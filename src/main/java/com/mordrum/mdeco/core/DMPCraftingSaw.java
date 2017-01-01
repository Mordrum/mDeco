package com.mordrum.mdeco.core;

import com.mordrum.mdeco.object.DMPWorkbenchSawMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DMPCraftingSaw {
   public boolean doesInputMatchOutput(Item itemInput, int variantInput, Item itemOutput) {
      DMPWorkbenchSawMaterials[] var4 = DMPWorkbenchSawMaterials.values();
      int var5 = var4.length;

	   for (DMPWorkbenchSawMaterials materials : var4) {
		   if (materials.itemInput == itemInput && materials.variantInput == variantInput &&
				   materials.itemOutput == itemOutput) {
			   return true;
		   }
	   }

      return false;
   }

   public int getOutputAmountFromOutputItem(Item itemOutput) {
      if(itemOutput == null) {
         return 0;
      } else {
         DMPWorkbenchSawMaterials[] var2 = DMPWorkbenchSawMaterials.values();
         int var3 = var2.length;

	      for (DMPWorkbenchSawMaterials materials : var2) {
		      if (materials.itemOutput == itemOutput) {
			      return materials.outputAmount;
		      }
	      }

         return 0;
      }
   }

   public boolean isWorkbenchSawInputMaterial(Item itemInput, int variantInput) {
      if(itemInput != null && variantInput >= 0 && variantInput <= 15) {
         DMPWorkbenchSawMaterials[] var3 = DMPWorkbenchSawMaterials.values();
         int var4 = var3.length;

	      for (DMPWorkbenchSawMaterials materials : var3) {
		      if (materials.itemInput == itemInput && materials.variantInput == variantInput) {
			      return true;
		      }
	      }

         return false;
      } else {
         return false;
      }
   }

   public boolean isWorkbenchSawOutputMaterial(Item itemOutput) {
      if(itemOutput == null) {
         return false;
      } else {
         DMPWorkbenchSawMaterials[] var2 = DMPWorkbenchSawMaterials.values();
         int var3 = var2.length;

	      for (DMPWorkbenchSawMaterials materials : var2) {
		      if (materials.itemOutput == itemOutput) {
			      return true;
		      }
	      }

         return false;
      }
   }

   public ItemStack getWorkbenchSawRecipeOutput(ItemStack inputStack) {
      if(inputStack == null) {
         return null;
      } else {
         Item itemInput = inputStack.getItem();
         int variantInput = inputStack.getItemDamage();
         DMPWorkbenchSawMaterials[] var4 = DMPWorkbenchSawMaterials.values();
         int var5 = var4.length;

	      for (DMPWorkbenchSawMaterials materials : var4) {
		      if (materials.itemInput == itemInput && materials.variantInput == variantInput) {
			      return new ItemStack(materials.itemOutput, materials.outputAmount, 0);
		      }
	      }

         return null;
      }
   }
}
