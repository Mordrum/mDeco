package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemCurtainRod;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockCurtainRod extends DMPBlockDirectional {
   public static final PropertyBool CONNECTED = PropertyBool.create("connected");
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 0.25D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 0.25D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 0.25D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

   public DMPBlockCurtainRod(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, Boolean.FALSE).withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemCurtainRod.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, CONNECTED, FACING);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
      return blockBelow != null && blockBelow instanceof DMPBlockCurtain?state.withProperty(CONNECTED, Boolean.TRUE):state.withProperty(CONNECTED, Boolean.FALSE);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return side != EnumFacing.DOWN && side != EnumFacing.UP;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         return AABB_N;
      case 2:
         return AABB_S;
      case 3:
         return AABB_W;
      case 4:
         return AABB_E;
      default:
         return FULL_BLOCK_AABB;
      }
   }
}
