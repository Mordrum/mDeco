package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.tileentity.DMPTileEntityMarketCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPContainerMarketCrate extends Container {
   private DMPTileEntityMarketCrate tileEntityMarketCrate;
   private IInventory storageInventory;
   private int numSlots;

   public DMPContainerMarketCrate(World worldIn, BlockPos pos, EntityPlayer player) {
      this.tileEntityMarketCrate = (DMPTileEntityMarketCrate)worldIn.getTileEntity(pos);
      this.storageInventory = (IInventory)worldIn.getTileEntity(pos);
      this.storageInventory.openInventory(player);
      InventoryPlayer playerInventory = player.inventory;
      playerInventory.openInventory(player);
      this.numSlots = playerInventory.getSizeInventory();
      int slotIndex = 0;

      int j;
      int k;
      for(j = 0; j < 3; ++j) {
         for(k = 0; k < 3; ++k) {
            this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 26 + k * 18, 18 + j * 18));
         }
      }

      for(j = 0; j < 2; ++j) {
         for(k = 0; k < 2; ++k) {
            this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 116 + k * 18, 28 + j * 18));
         }
      }

      this.addSlotToContainer(new DyeMaterial(this.storageInventory, 13, 36, 74));
      this.addSlotToContainer(new DyeMaterial(this.storageInventory, 14, 36, 94));
      int playerSlots = 0;

      for(j = 0; j < 9; ++j) {
         this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + j * 18, 184));
      }

      for(j = 0; j < 3; ++j) {
         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + k * 18, 126 + j * 18));
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.storageInventory.isUsableByPlayer(player);
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

   public void updateDisplayText(String text1, String text2) {
      this.tileEntityMarketCrate.setDisplayText(text1, text2);
   }

   public String getDisplayText(int row) {
      return row == 2?this.tileEntityMarketCrate.getDisplayText(2):this.tileEntityMarketCrate.getDisplayText(1);
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
}
