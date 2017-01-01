package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockWoodTimberConnector;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemWoodTimberConnector extends ItemBlock {

	public DMPItemWoodTimberConnector(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockWoodTimberConnector decorationBlock = (DMPBlockWoodTimberConnector) block;
   }
}
