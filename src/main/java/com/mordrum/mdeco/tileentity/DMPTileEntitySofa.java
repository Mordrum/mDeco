package com.mordrum.mdeco.tileentity;

import com.mordrum.mdeco.block.DMPBlockStorage;
import com.mordrum.mdeco.inventory.DMPContainerStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class DMPTileEntitySofa extends TileEntityLockable implements IInventory {
   private static final int TOTAL_SLOTS = 2;
   private ItemStack[] storageItems = new ItemStack[2];
   public boolean open = false;
   public int numPlayersUsing = 0;

   public int getSizeInventory() {
      return this.storageItems.length;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   public ItemStack getStackInSlot(int index) {
      return index >= this.storageItems.length?null:this.storageItems[index];
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
      return "DMPSofaTE";
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

   public Packet getDescriptionPacket() {
      NBTTagCompound nbttagcompound1 = new NBTTagCompound();
      this.writeToNBT(nbttagcompound1);
      return new SPacketUpdateTileEntity(this.pos, 3, nbttagcompound1);
   }

   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
      this.readFromNBT(pkt.getNbtCompound());
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   @Override
   public boolean isUsableByPlayer(EntityPlayer player) {
      return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(
              (double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <=
              64.0D;
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

   public int getField(int id) {
      return Item.getIdFromItem(this.storageItems[id].getItem());
   }

   public void setField(int id, int value) {
      this.storageItems[id] = new ItemStack(Item.getItemById(value));
   }

   public int getFieldCount() {
      return this.storageItems.length;
   }

   public void clear() {
      for(int i = 0; i < this.storageItems.length; ++i) {
         this.storageItems[i] = null;
      }

   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName()?new TextComponentString(this.getName()):new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      return new DMPContainerStorage(playerInventory, this, playerIn);
   }

   public String getGuiID() {
      return "mdeco:sofa";
   }

   public boolean hasBackCushion() {
      return this.storageItems[0] != null;
   }

   public boolean hasSeatCushion() {
      return this.storageItems[1] != null;
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
