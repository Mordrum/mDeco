package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemBrickHeadstone;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockBrickHeadstone extends Block {
   public static final PropertyEnum FACING = PropertyEnum.create("facing", EnumDirection.class);
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
   protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.9375D, 0.9375D, 0.625D);
   protected static final AxisAlignedBB AABB_WE = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.9375D, 0.9375D);
   protected static final AxisAlignedBB AABB_TALL_NS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.4375D, 0.625D);
   protected static final AxisAlignedBB AABB_TALL_WE = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.4375D, 1.0D);

   public DMPBlockBrickHeadstone(String unlocalizedName) {
      super(Material.GROUND, MapColor.STONE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumDirection.north_south).withProperty(VARIANT, EnumType.short_cross));
      this.setTickRandomly(false);
      this.setUnlocalizedName(unlocalizedName);
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      this.setHardness(1.5F);
      this.setResistance(10.0F);
      this.setSoundType(SoundType.STONE);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemBrickHeadstone.class, unlocalizedName);
      OreDictionary.registerOre(unlocalizedName, this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING, VARIANT);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumDirection.byMetadata((meta & 8) >> 3)).withProperty(VARIANT, EnumType.byMetadata(meta & 7));
   }

   public int getMetaFromState(IBlockState state) {
      byte b0 = 0;
      int i = b0 | ((EnumType)state.getValue(VARIANT)).getMetadata();
      i |= ((EnumDirection)state.getValue(FACING)).getMetadata() << 3;
      return i;
   }

   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      EnumDirection facing = (EnumDirection)state.getValue(FACING);
      EnumType variant = (EnumType)state.getValue(VARIANT);
      return variant == EnumType.tall_cross?(facing == EnumDirection.west_east?AABB_TALL_WE:AABB_TALL_NS):(facing == EnumDirection.west_east?AABB_WE:AABB_NS);
   }

   @SideOnly(Side.CLIENT)
   public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      return this.getCollisionBoundingBox(state, worldIn, pos).offset(pos);
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public int damageDropped(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
      EnumType[] var4 = EnumType.values();
      int var5 = var4.length;

	   for (EnumType headstone$enumtype : var4) {
		   list.add(new ItemStack(itemIn, 1, headstone$enumtype.getMetadata()));
	   }

   }

   public enum EnumType implements IStringSerializable {
      short_cross(0),
      tall_cross(1),
      rounded(2);

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

	      for (EnumType headstone$enumtype : var0) {
		      META_LOOKUP[headstone$enumtype.getMetadata()] = headstone$enumtype;
	      }

      }
   }

   public enum EnumDirection implements IStringSerializable {
      north_south(0),
      west_east(1);

      private static final EnumDirection[] META_LOOKUP = new EnumDirection[values().length];
      private final int meta;

      EnumDirection(int meta) {
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

      public static EnumDirection byMetadata(int meta) {
         if(meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      static {
         EnumDirection[] var0 = values();
         int var1 = var0.length;

	      for (EnumDirection headstone$enumfacing : var0) {
		      META_LOOKUP[headstone$enumfacing.getMetadata()] = headstone$enumfacing;
	      }

      }
   }
}
