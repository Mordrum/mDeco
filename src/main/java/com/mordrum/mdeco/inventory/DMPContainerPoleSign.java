package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPContainerPoleSign extends Container {
   private DMPTileEntityPoleSign tileEntityPoleSign;
   private IInventory storageInventory;
   private int numSlots;

   public DMPContainerPoleSign(World worldIn, BlockPos pos, EntityPlayer player) {
      this.tileEntityPoleSign = (DMPTileEntityPoleSign)worldIn.getTileEntity(pos);
      this.storageInventory = (IInventory)worldIn.getTileEntity(pos);
      this.storageInventory.openInventory(player);
      InventoryPlayer playerInventory = player.inventory;
      playerInventory.openInventory(player);
      this.numSlots = playerInventory.getSizeInventory();
      this.addSlotToContainer(new DyeMaterial(this.storageInventory, 0, 18, 18));
      int playerSlots = 0;

      int j;
      for(j = 0; j < 9; ++j) {
         this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + j * 18, 108));
      }

      for(j = 0; j < 3; ++j) {
         for(int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + k * 18, 50 + j * 18));
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.storageInventory.isUsableByPlayer(player);
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = null;
      Slot slot = this.inventorySlots.get(index);
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
            slot.putStack(null);
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

   public void updateSignText(String text) {
      this.tileEntityPoleSign.setSignText(text);
   }

   public String getSignText() {
      return this.tileEntityPoleSign.getSignText();
   }

   class DyeMaterial extends Slot {
      public DyeMaterial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
         super(inventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(ItemStack stack) {
         return stack.getItem() == Items.DYE;
      }

      public int getSlotStackLimit() {
         return 1;
      }
   }

   class InputMaterial extends Slot {
      public InputMaterial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
         super(inventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(ItemStack stack) {
         return true;
      }

      public int getSlotStackLimit() {
         return 1;
      }
   }
}
