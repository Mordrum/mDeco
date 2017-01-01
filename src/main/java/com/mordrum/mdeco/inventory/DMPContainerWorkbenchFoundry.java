package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.core.DMPCraftingFoundry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPContainerWorkbenchFoundry extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public IInventory craftResult = new InventoryCraftResult();
   private World world;
   private BlockPos blockPos;

   public DMPContainerWorkbenchFoundry(World worldIn, BlockPos pos, EntityPlayer player) {
      this.world = worldIn;
      this.blockPos = pos;
      this.addSlotToContainer(new DMPSlotCraftingFoundry(player, this.craftMatrix, this.craftResult, 0, 124, 35));

      int i;
      int j;
      for(i = 0; i < 3; ++i) {
         for(j = 0; j < 3; ++j) {
            this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
         }
      }

      for(i = 0; i < 3; ++i) {
         for(j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   public void onCraftMatrixChanged(IInventory inventoryIn) {
      this.craftResult.setInventorySlotContents(0, DMPCraftingFoundry.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
   }

   public void onContainerClosed(EntityPlayer playerIn) {
      super.onContainerClosed(playerIn);
      if(!this.world.isRemote) {
         for(int i = 0; i < 9; ++i) {
            ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);
            if(itemstack != null) {
               playerIn.dropItem(itemstack, false);
            }
         }
      }

   }

   public boolean canInteractWith(EntityPlayer playerIn) {
      return this.world.getBlockState(this.blockPos).getBlock() == MDeco.blocks.workbenchFoundry &&
		      playerIn.getDistanceSq(
				      (double) this.blockPos.getX() + 0.5D,
				      (double) this.blockPos.getY() + 0.5D, (double) this.blockPos.getZ() + 0.5D) <= 64.0D;
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = null;
      Slot slot = this.inventorySlots.get(index);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(index == 0) {
            if(!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if(index >= 10 && index < 37) {
            if(!this.mergeItemStack(itemstack1, 37, 46, false)) {
               return null;
            }
         } else if(index >= 37 && index < 46) {
            if(!this.mergeItemStack(itemstack1, 10, 37, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(itemstack1, 10, 46, false)) {
            return null;
         }

         if(itemstack1.getCount() == 0) {
            slot.putStack(null);
         } else {
            slot.onSlotChanged();
         }

         if(itemstack1.getCount() == itemstack.getCount()) {
            return null;
         }

         slot.onTake(playerIn, itemstack1);
      }

      return itemstack;
   }

   public boolean canMergeSlot(ItemStack stackIn, Slot slotIn) {
      return slotIn.inventory != this.craftResult && super.canMergeSlot(stackIn, slotIn);
   }
}
