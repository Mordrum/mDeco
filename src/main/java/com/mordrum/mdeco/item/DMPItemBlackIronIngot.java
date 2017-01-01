package com.mordrum.mdeco.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPItemBlackIronIngot extends Item {
   public DMPItemBlackIronIngot(String unlocalizedName) {
      this.setUnlocalizedName(unlocalizedName);
      this.setRegistryName(unlocalizedName);
      GameRegistry.register(this);
      this.setCreativeTab(CreativeTabs.MATERIALS);
   }
}
