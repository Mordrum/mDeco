package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockPalletStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemPalletStack extends ItemBlock {

	public DMPItemPalletStack(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockPalletStack decorationBlock = (DMPBlockPalletStack) block;
   }

   public int getMetadata(int damage) {
      return damage;
   }
}
