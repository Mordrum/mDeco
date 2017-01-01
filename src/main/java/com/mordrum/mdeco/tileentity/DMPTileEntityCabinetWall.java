package com.mordrum.mdeco.tileentity;

import com.mordrum.mdeco.block.DMPBlockStorage;
import com.mordrum.mdeco.inventory.DMPContainerStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DMPTileEntityCabinetWall extends DMPTileEntityStorage {
   private ItemStack[] storageItems = new ItemStack[28];
   public boolean open = false;
   public int numPlayersUsing = 0;

   public int getSizeInventory() {
      return 28;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   public ItemStack getStackInSlot(int index) {
      return this.storageItems[index];
   }

   public ItemStack decrStackSize(int index, int count) {
      if(this.storageItems[index] != null) {
         ItemStack itemstack;
         if(this.storageItems[index].getCount() <= count) {
            itemstack = this.storageItems[index];
            this.storageItems[index] = null;
            this.markDirty();
            return itemstack;
         } else {
            itemstack = this.storageItems[index].splitStack(count);
            if(this.storageItems[index].getCount() == 0) {
               this.storageItems[index] = null;
            }

            this.markDirty();
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int index, ItemStack stack) {
      this.storageItems[index] = stack;
      if(stack != null && stack.getCount() > this.getInventoryStackLimit()) {
         stack.setCount(this.getInventoryStackLimit());
      }

      this.markDirty();
   }

   public String getName() {
      return "container.cabinetWall";
   }

   public String getGuiID() {
      return "mdeco:cabinetWall";
   }

   public boolean hasCustomName() {
      return false;
   }

   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      NBTTagList nbttaglist = compound.getTagList("Items", 10);
      this.storageItems = new ItemStack[this.getSizeInventory()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if(j >= 0 && j < this.storageItems.length) {
            this.storageItems[j] = new ItemStack(nbttagcompound1);
         }
      }

   }

   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.storageItems.length; ++i) {
         if(this.storageItems[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.storageItems[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      compound.setTag("Items", nbttaglist);
      return compound;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUsableByPlayer(EntityPlayer player) {
      return this.world.getTileEntity(this.pos) != this?false:player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
   }

   public boolean receiveClientEvent(int id, int type) {
      if(id == 1) {
         this.numPlayersUsing = type;
         return true;
      } else {
         return super.receiveClientEvent(id, type);
      }
   }

   public void openInventory(EntityPlayer player) {
      if(!player.isSpectator()) {
         if(this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         ++this.numPlayersUsing;
         this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), true);
      }

   }

   public void closeInventory(EntityPlayer player) {
      if(!player.isSpectator() && this.getBlockType() instanceof DMPBlockStorage) {
         --this.numPlayersUsing;
         this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), true);
      }

   }

   public boolean isItemValidForSlot(int index, ItemStack stack) {
      return true;
   }

   public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
   }

   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      return new DMPContainerStorage(playerInventory, this, playerIn);
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public void clear() {
      for(int i = 0; i < this.storageItems.length; ++i) {
         this.storageItems[i] = null;
      }

   }

   public ItemStack removeStackFromSlot(int index) {
      if(this.storageItems[index] != null) {
         ItemStack itemstack = this.storageItems[index];
         this.storageItems[index] = null;
         return itemstack;
      } else {
         return null;
      }
   }
}
