package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockChain;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class DMPItemChain extends ItemBlock {

	public DMPItemChain(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
		DMPBlockChain decorationBlock = (DMPBlockChain) block;
   }
}
