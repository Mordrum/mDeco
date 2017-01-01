package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemWoodTrim;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockWoodTrim extends DMPBlockDirectional {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 2);
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

   public DMPBlockWoodTrim(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWoodTrim.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECTED, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(CONNECTED, Integer.valueOf(this.getConnectedIndex(state, worldIn, pos)));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return (EnumFacing)base_state.getValue(FACING) == side;
   }

   private int getConnectedIndex(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      boolean up = worldIn.getBlockState(pos.up()).getBlock() == this;
      boolean down = worldIn.getBlockState(pos.down()).getBlock() == this;
      boolean side1 = false;
      boolean side2 = false;
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         side1 = worldIn.getBlockState(pos.west()).getBlock() == this;
         side2 = worldIn.getBlockState(pos.east()).getBlock() == this;
         break;
      case 2:
         side1 = worldIn.getBlockState(pos.east()).getBlock() == this;
         side2 = worldIn.getBlockState(pos.west()).getBlock() == this;
         break;
      case 3:
         side1 = worldIn.getBlockState(pos.south()).getBlock() == this;
         side2 = worldIn.getBlockState(pos.north()).getBlock() == this;
         break;
      case 4:
         side1 = worldIn.getBlockState(pos.north()).getBlock() == this;
         side2 = worldIn.getBlockState(pos.south()).getBlock() == this;
         break;
      default:
         return 0;
      }

      return !up && !down && side1 && side2?2:(up && down && !side1 && !side2?1:0);
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
