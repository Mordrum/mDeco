package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockStandSmall;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemStandSmall extends ItemBlock {
   private DMPBlockStandSmall decorationBlock;

   public DMPItemStandSmall(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockStandSmall)block;
   }
}
