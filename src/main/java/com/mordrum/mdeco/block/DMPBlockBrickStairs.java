package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPTab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPBlockBrickStairs extends BlockStairs {
   public DMPBlockBrickStairs(String unlocalizedName) {
      super(Blocks.STONE_STAIRS.getDefaultState());
      this.setSoundType(SoundType.STONE);
      this.setUnlocalizedName(unlocalizedName);
      this.setHardness(1.2F);
      this.useNeighborBrightness = true;
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      this.setRegistryName(unlocalizedName);
      GameRegistry.register(this);
   }
}
