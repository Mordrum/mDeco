package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemBrick;
import com.mordrum.mdeco.object.DMPTab;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockBrick extends Block {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

   public DMPBlockBrick(String unlocalizedName) {
      super(Material.GROUND, MapColor.STONE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.normal));
      this.setTickRandomly(false);
      this.setUnlocalizedName(unlocalizedName);
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      this.setHardness(1.5F);
      this.setResistance(10.0F);
      this.setSoundType(SoundType.STONE);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemBrick.class, unlocalizedName);
      OreDictionary.registerOre(unlocalizedName, this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, VARIANT);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public int damageDropped(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
      EnumType[] var4 = EnumType.values();
      int var5 = var4.length;

	   for (EnumType blockbrick$enumtype : var4) {
		   list.add(new ItemStack(itemIn, 1, blockbrick$enumtype.getMetadata()));
	   }

   }

   public enum EnumType implements IStringSerializable {
      normal(0),
      mossy(1);

      private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;

      EnumType(int meta) {
         this.meta = meta;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String getName() {
         return this.name();
      }

      public String toString() {
         return this.getName();
      }

      public static EnumType byMetadata(int meta) {
         if(meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      static {
         EnumType[] var0 = values();
         int var1 = var0.length;

	      for (EnumType blockbrick$enumtype : var0) {
		      META_LOOKUP[blockbrick$enumtype.getMetadata()] = blockbrick$enumtype;
	      }

      }
   }
}
