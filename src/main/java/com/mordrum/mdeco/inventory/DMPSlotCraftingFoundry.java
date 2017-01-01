package com.mordrum.mdeco.inventory;

import com.mordrum.mdeco.core.DMPCraftingFoundry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class DMPSlotCraftingFoundry extends Slot {
   private final InventoryCrafting craftMatrix;
   private final EntityPlayer thePlayer;
   private int amountCrafted;

   public DMPSlotCraftingFoundry(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventory, int slotIndex, int xPosition, int yPosition) {
      super(inventory, slotIndex, xPosition, yPosition);
      this.thePlayer = player;
      this.craftMatrix = craftingInventory;
   }

   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   public ItemStack decrStackSize(int amount) {
      if(this.getHasStack()) {
         this.amountCrafted += Math.min(amount, this.getStack().getCount());
      }

      return super.decrStackSize(amount);
   }

   protected void onCrafting(ItemStack stack, int amount) {
      this.amountCrafted += amount;
      this.onCrafting(stack);
   }

   protected void onCrafting(ItemStack stack) {
      if(this.amountCrafted > 0) {
         stack.onCrafting(this.thePlayer.world, this.thePlayer, this.amountCrafted);
      }

      this.amountCrafted = 0;
   }

   public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
      this.onCrafting(stack);
      NonNullList<ItemStack> aitemstack = DMPCraftingFoundry.getInstance().getRecipeItemStack(this.craftMatrix, playerIn.world);

      for(int i = 0; i < aitemstack.size(); ++i) {
         ItemStack itemstack1 = this.craftMatrix.getStackInSlot(i);
         ItemStack itemstack2 = aitemstack.get(i);
         if(itemstack1 != null) {
            this.craftMatrix.decrStackSize(i, 1);
         }

         if(itemstack2 != null) {
            if(this.craftMatrix.getStackInSlot(i) == null) {
               this.craftMatrix.setInventorySlotContents(i, itemstack2);
            } else if(!this.thePlayer.inventory.addItemStackToInventory(itemstack2)) {
               this.thePlayer.dropItem(itemstack2, false);
            }
         }
      }

   }
}
