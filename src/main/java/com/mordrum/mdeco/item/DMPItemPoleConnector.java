package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockPoleConnector;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemPoleConnector extends ItemBlock {

	public DMPItemPoleConnector(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockPoleConnector decorationBlock = (DMPBlockPoleConnector) block;
   }
}
