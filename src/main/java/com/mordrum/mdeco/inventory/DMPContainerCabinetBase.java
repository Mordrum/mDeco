package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.block.DMPBlockTile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPContainerCabinetBase extends Container {
   private IInventory storageInventory;
   private int numSlots;

   public DMPContainerCabinetBase(World worldIn, BlockPos pos, EntityPlayer player) {
      this.storageInventory = (IInventory)worldIn.getTileEntity(pos);
      this.storageInventory.openInventory(player);
      InventoryPlayer playerInventory = player.inventory;
      playerInventory.openInventory(player);
      this.numSlots = playerInventory.getSizeInventory();
      int slotIndex = 0;

      int k;
      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 8 + k * 18, 37));
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 8 + k * 18, 55));
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 8 + k * 18, 73));
      }

      for(k = 0; k < 9; ++k) {
         this.addSlotToContainer(new Slot(this.storageInventory, slotIndex++, 8 + k * 18, 91));
      }

      this.addSlotToContainer(new TileMaterial(this.storageInventory, slotIndex++, 89, 17));
      int playerSlots = 0;

      int j;
      for(j = 0; j < 9; ++j) {
         this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + j * 18, 180));
      }

      for(j = 0; j < 3; ++j) {
         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(playerInventory, playerSlots++, 8 + k * 18, 122 + j * 18));
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return true;
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

   class TileMaterial extends Slot {
      public TileMaterial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
         super(inventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(ItemStack stack) {
         Item item = stack.getItem();
         if(item == null) {
            return false;
         } else {
            Block block = Block.getBlockFromItem(item);
            return block != null && block instanceof DMPBlockTile;
         }
      }

      public int getSlotStackLimit() {
         return 1;
      }
   }
}
