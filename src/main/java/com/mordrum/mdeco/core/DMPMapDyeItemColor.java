package com.mordrum.mdeco.core;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class DMPMapDyeItemColor {
   public static int getColorFromDyeItem(ItemStack itemStack) {
      if(itemStack == null) {
         return 16777215;
      } else {
         Item item = itemStack.getItem();
         if(item != null && item == Items.DYE) {
            int color = itemStack.getItemDamage();
            switch(color) {
            case 0:
               return 0;
            case 1:
               return 12132898;
            case 2:
               return 4491315;
            case 3:
               return 7685397;
            case 4:
               return 3162304;
            case 5:
               return 9457840;
            case 6:
               return 4227216;
            case 7:
               return 10066329;
            case 8:
               return 5000268;
            case 9:
               return 15892389;
            case 10:
               return 8441888;
            case 11:
               return 15790128;
            case 12:
               return 6328512;
            case 13:
               return 13652176;
            case 14:
               return 14716960;
            case 15:
               return 16777215;
            default:
               return 16777215;
            }
         } else {
            return 16777215;
         }
      }
   }
}
