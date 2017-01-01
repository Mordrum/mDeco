package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockIngotStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemIngotStack extends ItemBlock {

	public DMPItemIngotStack(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockIngotStack decorationBlock = (DMPBlockIngotStack) block;
   }

   public int getMetadata(int damage) {
      return damage;
   }
}
