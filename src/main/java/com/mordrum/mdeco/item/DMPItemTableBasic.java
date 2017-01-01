package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockTableBasic;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemTableBasic extends ItemBlock {

	public DMPItemTableBasic(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockTableBasic decorationBlock = (DMPBlockTableBasic) block;
   }
}
