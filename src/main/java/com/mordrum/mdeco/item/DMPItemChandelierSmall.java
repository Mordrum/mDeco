package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockChandelierSmall;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemChandelierSmall extends ItemBlock {

	public DMPItemChandelierSmall(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockChandelierSmall decorationBlock = (DMPBlockChandelierSmall) block;
   }
}
