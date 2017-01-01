package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemKitchenWallUtensils;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockKitchenWallUtensils extends DMPBlockBaseDecoration {
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   public static final PropertyDirection FACING;
   public static final PropertyEnum VARIANT;

   public DMPBlockKitchenWallUtensils(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.set_0));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemKitchenWallUtensils.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING, VARIANT});
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal((meta & 12) >> 2)).withProperty(VARIANT, EnumType.byMetadata(meta & 3));
   }

   public int getMetaFromState(IBlockState state) {
      byte b0 = 0;
      int i = b0 | ((EnumType)state.getValue(VARIANT)).getMetadata();
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex() << 2;
      return i;
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      EnumType type = (EnumType)state.getValue(VARIANT);
      worldIn.setBlockState(pos, state.withProperty(VARIANT, type.getNextVariant()), 2);
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         return DMPBlockWoodPanel.AABB_N;
      case 2:
         return DMPBlockWoodPanel.AABB_S;
      case 3:
         return DMPBlockWoodPanel.AABB_W;
      case 4:
         return DMPBlockWoodPanel.AABB_E;
      default:
         return FULL_BLOCK_AABB;
      }
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
      VARIANT = PropertyEnum.create("variant", EnumType.class);
   }

   public static enum EnumType implements IStringSerializable {
      set_0(0),
      set_1(1),
      set_2(2);

      private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;

      private EnumType(int meta) {
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

      public EnumType getNextVariant() {
         int meta = this.getMetadata() + 1;
         if(meta > META_LOOKUP.length - 1) {
            meta = 0;
         }

         return byMetadata(meta);
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

         for(int var2 = 0; var2 < var1; ++var2) {
            EnumType decoration$enumtype = var0[var2];
            META_LOOKUP[decoration$enumtype.getMetadata()] = decoration$enumtype;
         }

      }
   }
}
