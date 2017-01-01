package com.mordrum.mdeco.core;

import com.mordrum.mdeco.item.DMPItemBlackIronIngot;
import com.mordrum.mdeco.item.DMPItemBlackIronNugget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class DMPItems {
   public DMPItemBlackIronIngot blackIronIngot = new DMPItemBlackIronIngot("blackIronIngot");
   public DMPItemBlackIronNugget blackIronNugget;

   public DMPItems() {
      DMPReference.addToRegisteredItems(1);
      this.blackIronNugget = new DMPItemBlackIronNugget("blackIronNugget");
      DMPReference.addToRegisteredItems(1);
   }

   public boolean init() {
      RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
      if(renderItem == null) {
         return false;
      } else {
         renderItem.getItemModelMesher().register(this.blackIronIngot, 0, new ModelResourceLocation("mdeco:blackIronIngot", "inventory"));
         renderItem.getItemModelMesher().register(this.blackIronNugget, 0, new ModelResourceLocation("mdeco:blackIronNugget", "inventory"));
         return true;
      }
   }

   public boolean isItemMetalIngot(Item item) {
      return item != null && (item == Items.IRON_INGOT || item == Items.GOLD_INGOT || item == this.blackIronIngot);
   }
}
