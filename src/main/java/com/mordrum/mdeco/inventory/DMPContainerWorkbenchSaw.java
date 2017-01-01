package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.MDeco;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPContainerWorkbenchSaw extends Container {
   private IInventory tileEntityWorkbenchSaw;
   private final Slot inputSlot;
   private int cutTime;

   public DMPContainerWorkbenchSaw(World worldIn, BlockPos pos, EntityPlayer player) {
      this.tileEntityWorkbenchSaw = (IInventory)worldIn.getTileEntity(pos);
      this.addSlotToContainer(new OutputMaterial(this.tileEntityWorkbenchSaw, 0, 107, 21));
      this.inputSlot = this.addSlotToContainer(new InputMaterial(this.tileEntityWorkbenchSaw, 1, 49, 21));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 56 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 114));
      }

   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.listeners.size(); ++i) {
         IContainerListener icrafting = (IContainerListener) this.listeners.get(i);
         if(this.cutTime != this.tileEntityWorkbenchSaw.getField(0)) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileEntityWorkbenchSaw.getField(0));
         }
      }

      this.cutTime = this.tileEntityWorkbenchSaw.getField(0);
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int id, int data) {
      this.tileEntityWorkbenchSaw.setField(id, data);
   }

   public boolean canInteractWith(EntityPlayer playerIn) {
      return this.tileEntityWorkbenchSaw.isUsableByPlayer(playerIn);
   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(index >= 0 && index <= 1) {
            if(!this.mergeItemStack(itemstack1, 2, 38, true)) {
               return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if(!this.inputSlot.getHasStack() && this.inputSlot.isItemValid(itemstack1)) {
            if(!this.mergeItemStack(itemstack1, 1, 2, false)) {
               return null;
            }
         } else if(OutputMaterial.canHoldOutputMaterial(itemstack)) {
            if(!this.mergeItemStack(itemstack1, 0, 3, false)) {
               return null;
            }
         } else if(index >= 2 && index < 29) {
            if(!this.mergeItemStack(itemstack1, 29, 38, false)) {
               return null;
            }
         } else if(index >= 29 && index < 38) {
            if(!this.mergeItemStack(itemstack1, 2, 29, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(itemstack1, 2, 38, false)) {
            return null;
         }

         if(itemstack1.getCount() == 0) {
            slot.putStack((ItemStack)null);
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

   static class OutputMaterial extends Slot {
      public OutputMaterial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
         super(inventoryIn, index, xPosition, yPosition);
      }

      public int getSlotStackLimit() {
         return 1;
      }

      public boolean isItemValid(ItemStack stack) {
         return canHoldOutputMaterial(stack);
      }

      public static boolean canHoldOutputMaterial(ItemStack stack) {
         return false;
      }
   }

   class InputMaterial extends Slot {
      public InputMaterial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
         super(inventoryIn, index, xPosition, yPosition);
      }

      public boolean isItemValid(ItemStack stack) {
         return stack != null? MDeco.recipes.workbenchSawRecipes.isWorkbenchSawInputMaterial(stack.getItem(), stack.getItemDamage()):false;
      }

      public int getSlotStackLimit() {
         return 64;
      }
   }
}
