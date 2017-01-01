package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockBrickSlabDouble;
import com.mordrum.mdeco.block.DMPBlockBrickSlabHalf;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class DMPItemBrickSlab extends ItemSlab {
   public DMPItemBrickSlab(Block block, DMPBlockBrickSlabHalf singleSlab, DMPBlockBrickSlabDouble doubleSlab) {
      super(block, singleSlab, doubleSlab);
   }
}
