package com.mordrum.mdeco.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPBlockBlackIron extends Block {
   public DMPBlockBlackIron(String unlocalizedName) {
      super(Material.IRON);
      this.setHardness(5.0F);
      this.setResistance(10.0F);
      this.setSoundType(SoundType.METAL);
      this.setUnlocalizedName(unlocalizedName);
      this.setTickRandomly(false);
      this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      this.setRegistryName(unlocalizedName);
      GameRegistry.register(this);
   }
}
