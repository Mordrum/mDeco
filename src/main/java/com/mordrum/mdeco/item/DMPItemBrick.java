package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockBrick;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPItemBrick extends ItemBlock {
   public DMPItemBrick(Block block) {
      super(block);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int damageValue) {
      return damageValue;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
      this.block.getSubBlocks(itemIn, tab, subItems);
   }

   public String getUnlocalizedName(ItemStack itemStack) {
      return String.format("%s_%s", this.block.getUnlocalizedName(), DMPBlockBrick.EnumType.byMetadata(itemStack.getMetadata()).name());
   }
}
