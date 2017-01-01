package com.mordrum.mdeco.tileentity;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.block.DMPBlockWorkbenchSaw;
import com.mordrum.mdeco.inventory.DMPContainerWorkbenchSaw;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPTileEntityWorkbenchSaw extends TileEntityLockable implements ISidedInventory, ITickable {
   private static final int OUTPUT_SLOT_ID = 0;
   private static final int INPUT_SLOT_ID = 1;
   private static final int TOTAL_SLOTS = 2;
   private static final int[] outputSlots = new int[]{0};
   private static final int[] inputSlots = new int[]{1};
   private ItemStack[] cuttingItemStacks = new ItemStack[2];
   private int cutTime;
   private Item materialItem;
   public String customName;

   public String getName() {
      return this.hasCustomName()?this.customName:"container.workbenchSaw";
   }

   public boolean hasCustomName() {
      return false;
   }

   public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
      return oldState.getBlock() != newSate.getBlock();
   }

   public int getSizeInventory() {
      this.validate();
      return this.cuttingItemStacks.length;
   }

   @Override public boolean isEmpty() {
      return false;
   }

   public void update() {
      boolean canCut = this.canCut();
      boolean updateState = false;
      if(this.cutTime > 0) {
         --this.cutTime;
         if(this.cutTime <= 0) {
            this.cutTime = 0;
            this.produceOutput();
            this.markDirty();
            updateState = true;
         } else if(!canCut) {
            this.cutTime = 0;
            this.markDirty();
            updateState = true;
         } else if(this.materialItem != this.cuttingItemStacks[1].getItem()) {
            this.cutTime = 0;
            this.markDirty();
            updateState = true;
         }
      } else if(canCut) {
         this.cutTime = MDeco.settings.workbenchSawCutTime;
         this.materialItem = this.cuttingItemStacks[1].getItem();
         updateState = true;
      }

      if(!this.world.isRemote && updateState) {
         IBlockState iblockstate = this.world.getBlockState(this.getPos());
         if(iblockstate.getBlock() instanceof DMPBlockWorkbenchSaw) {
            boolean newState = canCut && this.cutTime > 0;
            if(((Boolean)iblockstate.getValue(DMPBlockWorkbenchSaw.ACTIVE)).booleanValue() != newState) {
               iblockstate = iblockstate.withProperty(DMPBlockWorkbenchSaw.ACTIVE, Boolean.valueOf(newState));
               this.world.setBlockState(this.getPos(), iblockstate, 2);
            }
         }
      }

   }

   private boolean canCut() {
      if(this.cuttingItemStacks[1] != null && this.cuttingItemStacks[1].getCount() > 0) {
         Item itemInput = this.cuttingItemStacks[1].getItem();
         if(itemInput == null) {
            return false;
         }

         if(!MDeco.recipes.workbenchSawRecipes.isWorkbenchSawInputMaterial(itemInput, this.cuttingItemStacks[1].getItemDamage())) {
            return false;
         }

         if(this.cuttingItemStacks[0] == null) {
            return true;
         }

         Item itemOutput = this.cuttingItemStacks[0].getItem();
         if(itemOutput == null) {
            return false;
         }

         if(!MDeco.recipes.workbenchSawRecipes.doesInputMatchOutput(itemInput, this.cuttingItemStacks[1].getItemDamage(), itemOutput)) {
            return false;
         }

         if(this.cuttingItemStacks[0].getCount() < 64 - MDeco.recipes.workbenchSawRecipes.getOutputAmountFromOutputItem(itemOutput)) {
            return true;
         }
      }

      return false;
   }

   private void produceOutput() {
      if(this.canCut()) {
         ItemStack inputStack = this.cuttingItemStacks[1];
         if(inputStack.getItem().hasContainerItem(inputStack)) {
            this.cuttingItemStacks[1] = inputStack.getItem().getContainerItem(inputStack);
         } else {
            this.cuttingItemStacks[1].setCount(this.cuttingItemStacks[1].getCount() - 1);
            if(this.cuttingItemStacks[1].getCount() <= 0) {
               this.cuttingItemStacks[1] = null;
            }

            if(this.cuttingItemStacks[0] == null) {
               this.cuttingItemStacks[0] = MDeco.recipes.workbenchSawRecipes.getWorkbenchSawRecipeOutput(inputStack);
            } else {
               ItemStack outputStack = this.cuttingItemStacks[0];
               ItemStack newOutput = MDeco.recipes.workbenchSawRecipes.getWorkbenchSawRecipeOutput(inputStack);
               if(outputStack.getItem() == newOutput.getItem()) {
                  this.cuttingItemStacks[0].setCount(this.cuttingItemStacks[0].getCount() + MDeco.recipes.workbenchSawRecipes.getOutputAmountFromOutputItem(newOutput.getItem()));
                  if(this.cuttingItemStacks[0].getCount() > 64) {
                     this.cuttingItemStacks[0].setCount(64);
                  }
               }
            }
         }
      }

   }

   public ItemStack getStackInSlot(int index) {
      return index >= 0 && index < this.cuttingItemStacks.length?this.cuttingItemStacks[index]:null;
   }

   public ItemStack decrStackSize(int index, int count) {
      if(index >= 0 && index < this.cuttingItemStacks.length) {
         ItemStack itemStack = this.cuttingItemStacks[index];
         this.cuttingItemStacks[index] = null;
         return itemStack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int index, ItemStack stack) {
      if(index >= 0 && index < this.cuttingItemStacks.length) {
         this.cuttingItemStacks[index] = stack;
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUsableByPlayer(EntityPlayer player) {
      return this.world.getTileEntity(this.pos) != this?false:player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
   }

   public void openInventory(EntityPlayer player) {
   }

   public void closeInventory(EntityPlayer player) {
   }

   public int[] getSlotsForFace(EnumFacing side) {
      return side == EnumFacing.UP?inputSlots:outputSlots;
   }

   public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
      return this.isItemValidForSlot(index, itemStackIn);
   }

   public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
      return true;
   }

   public String getGuiID() {
      return "mdeco:workbenchSaw";
   }

   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      return new DMPContainerWorkbenchSaw(playerIn.world, playerIn.getPosition(), playerIn);
   }

   public int getField(int id) {
      return id == 0?this.cutTime:0;
   }

   public void setField(int id, int value) {
      if(id == 0) {
         this.cutTime = value;
      }

   }

   public int getFieldCount() {
      return 1;
   }

   public void clear() {
      for(int i = 0; i < this.cuttingItemStacks.length; ++i) {
         this.cuttingItemStacks[i] = null;
      }

   }

   public boolean isItemValidForSlot(int index, ItemStack stack) {
      if(stack == null) {
         return false;
      } else if(index == 0) {
         return false;
      } else {
         Item item = stack.getItem();
         return item == null?false: MDeco.recipes.workbenchSawRecipes.isWorkbenchSawInputMaterial(item, stack.getItemDamage());
      }
   }

   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagList nbttaglist = compound.getTagList("Items", 10);
      this.cuttingItemStacks = new ItemStack[this.getSizeInventory()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if(j >= 0 && j < this.cuttingItemStacks.length) {
            this.cuttingItemStacks[j] = new ItemStack(nbttagcompound1);
         }
      }

      if(compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }

   }

   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.cuttingItemStacks.length; ++i) {
         if(this.cuttingItemStacks[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.cuttingItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      compound.setTag("Items", nbttaglist);
      if(this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   public ItemStack removeStackFromSlot(int index) {
      if(index >= 0 && index < this.cuttingItemStacks.length) {
         ItemStack itemstack = this.cuttingItemStacks[index];
         this.cuttingItemStacks[index] = null;
         return itemstack;
      } else {
         return null;
      }
   }
}
