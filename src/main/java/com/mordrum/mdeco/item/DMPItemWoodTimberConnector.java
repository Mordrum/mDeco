package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockWoodTimberConnector;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemWoodTimberConnector extends ItemBlock {
   private DMPBlockWoodTimberConnector decorationBlock;

   public DMPItemWoodTimberConnector(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockWoodTimberConnector)block;
   }
}
