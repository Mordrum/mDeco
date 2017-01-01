package com.mordrum.mdeco.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPContainerCurioTop extends Container {
   private IInventory storageInventory;
   private int numSlots;

   public DMPContainerCurioTop(World worldIn, BlockPos pos, EntityPlayer player) {
      this.storageInventory = (IInventory)worldIn.getTileEntity(pos);
      this.storageInventory.openInventory(player);
      InventoryPlayer playerInventory = player.inventory;
      playerInventory.openInventory(player);
      this.numSlots = playerInventory.getSizeInventory();
      byte slotIndex = 0;
      int var9 = slotIndex + 1;
      this.addSlotToContainer(new Slot(this.storageInventory, slotIndex, 32, 18));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 14, 36));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 50, 36));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 32, 54));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 128, 18));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 110, 36));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 146, 36));
      this.addSlotToContainer(new Slot(this.storageInventory, var9++, 128, 54));
      int playerSlots = 0;

      int j;
      for(j = 0; j < 9; ++j) {
         this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + j * 18, 144));
      }

      for(j = 0; j < 3; ++j) {
         for(int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + k * 18, 86 + j * 18));
         }
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
         if(index < this.numSlots) {
            if(!this.mergeItemStack(itemstack1, this.numSlots, this.inventorySlots.size(), true)) {
               return null;
            }
         } else if(!this.mergeItemStack(itemstack1, 0, this.numSlots, false)) {
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
