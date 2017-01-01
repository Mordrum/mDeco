package com.mordrum.mdeco.gui;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPGuiCreativeTab extends CreativeTabs {
   private DMPTab tab;

   public DMPGuiCreativeTab(DMPTab tab) {
      super(CreativeTabs.getNextID(), tab.name());
      this.tab = tab;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public ItemStack getTabIconItem() {
      switch(this.tab.ordinal()) {
      case 1:
         return new ItemStack(Item.getItemFromBlock((Block) MDeco.blocks.ancientBlocks.get("ancientBrickGray")));
      case 2:
         return new ItemStack(Item.getItemFromBlock((Block) MDeco.blocks.benchWoodMetalArm.get("benchWoodMetalArmOak")));
      default:
         return new ItemStack(Item.getItemFromBlock(Blocks.DEADBUSH));
      }
   }

   @SideOnly(Side.CLIENT)
   public String getTranslatedTabLabel() {
      return "creativeTab." + this.tab.name();
   }
}
