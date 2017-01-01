package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockConnectOne extends DMPBlockBaseDecoration {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 5);

   public DMPBlockConnectOne(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, 1));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, CONNECTED);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(CONNECTED);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(CONNECTED, meta);
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return true;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return worldIn.isAirBlock(pos);
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      float pixel = 0.0625F;
      float width = this.decoration.decorationType.getBoundingWidth() * pixel;
      float height = this.decoration.decorationType.getBoundingHeight() * pixel;
      int direction = state.getValue(CONNECTED);
      switch(direction) {
      case 0:
         return new AxisAlignedBB((double)(0.5F - width), (double)(1.0F - height), (double)(0.5F - width), (double)(0.5F + width), 1.0D, (double)(0.5F + width));
      case 1:
         return new AxisAlignedBB((double)(0.5F - width), 0.0D, (double)(0.5F - width), (double)(0.5F + width), (double)(0.0F + height), (double)(0.5F + width));
      case 2:
         return new AxisAlignedBB((double)(0.5F - width), (double)(0.5F - width), (double)(1.0F - height), (double)(0.5F + width), (double)(0.5F + width), 1.0D);
      case 3:
         return new AxisAlignedBB((double)(0.5F - width), (double)(0.5F - width), 0.0D, (double)(0.5F + width), (double)(0.5F + width), (double)(0.0F + height));
      case 4:
         return new AxisAlignedBB((double)(1.0F - height), (double)(0.5F - width), (double)(0.5F - width), 1.0D, (double)(0.5F + width), (double)(0.5F + width));
      case 5:
         return new AxisAlignedBB(0.0D, (double)(0.5F - width), (double)(0.5F - width), (double)(0.0F + height), (double)(0.5F + width), (double)(0.5F + width));
      default:
         return FULL_BLOCK_AABB;
      }
   }
}
