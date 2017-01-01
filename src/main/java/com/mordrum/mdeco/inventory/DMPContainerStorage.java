package com.mordrum.mdeco.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DMPContainerStorage extends Container {
   private IInventory storageInventory;
   private int numRows;

   public DMPContainerStorage(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
      this.storageInventory = chestInventory;
      this.numRows = chestInventory.getSizeInventory() / 9;
      chestInventory.openInventory(player);
      int i = (this.numRows - 4) * 18;

      int j;
      int k;
      for(j = 0; j < this.numRows; ++j) {
         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(chestInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
         }
      }

      for(j = 0; j < 3; ++j) {
         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
         }
      }

      for(j = 0; j < 9; ++j) {
         this.addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(index < this.numRows * 9) {
            if(!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
               return null;
            }
         } else if(!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
            return null;
         }

         if(itemstack1.getCount() == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return itemstack;
   }

   public void onContainerClosed(EntityPlayer playerIn) {
      super.onContainerClosed(playerIn);
      this.storageInventory.closeInventory(playerIn);
   }

   public IInventory getStorageInventory() {
      return this.storageInventory;
   }
}
