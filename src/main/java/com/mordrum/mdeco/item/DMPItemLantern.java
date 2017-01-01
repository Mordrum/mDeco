package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockLantern;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemLantern extends ItemBlock {
   private DMPBlockLantern decorationBlock;

   public DMPItemLantern(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockLantern)block;
   }
}
