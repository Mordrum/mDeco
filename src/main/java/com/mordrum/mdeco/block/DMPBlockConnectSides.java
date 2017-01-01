package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockConnectSides extends DMPBlockBaseDecoration {
   public static final PropertyDirection FACING;
   public static final PropertyBool LEFT;
   public static final PropertyBool RIGHT;
   protected boolean strictSideConnection = false;

   public DMPBlockConnectSides(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, Boolean.FALSE).withProperty(RIGHT, Boolean.FALSE));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING, LEFT, RIGHT);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      IBlockState actualState = worldIn.getBlockState(pos);
      if(actualState.getBlock() != this) {
         return state;
      } else {
         if(actualState.getValue(FACING) == EnumFacing.NORTH) {
            actualState = actualState.withProperty(LEFT, this.canConnectTo(worldIn, pos, EnumFacing.WEST)).withProperty(RIGHT, this
		            .canConnectTo(worldIn, pos, EnumFacing.EAST));
         } else if(actualState.getValue(FACING) == EnumFacing.EAST) {
            actualState = actualState.withProperty(LEFT, this.canConnectTo(worldIn, pos, EnumFacing.NORTH)).withProperty(RIGHT, this
		            .canConnectTo(worldIn, pos, EnumFacing.SOUTH));
         } else if(actualState.getValue(FACING) == EnumFacing.SOUTH) {
            actualState = actualState.withProperty(LEFT, this.canConnectTo(worldIn, pos, EnumFacing.EAST)).withProperty(RIGHT, this
		            .canConnectTo(worldIn, pos, EnumFacing.WEST));
         } else if(actualState.getValue(FACING) == EnumFacing.WEST) {
            actualState = actualState.withProperty(LEFT, this.canConnectTo(worldIn, pos, EnumFacing.SOUTH)).withProperty(RIGHT, this
		            .canConnectTo(worldIn, pos, EnumFacing.NORTH));
         }

         return actualState;
      }
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(FACING).getHorizontalIndex();
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP;
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
      Block block = worldIn.getBlockState(pos).getBlock();
      return block == this;
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing dirFromNewBlock) {
      IBlockState actualState = worldIn.getBlockState(pos);
      EnumFacing newBlockFacing = actualState.getValue(FACING);
      Block block = worldIn.getBlockState(pos.offset(dirFromNewBlock)).getBlock();
      if(block != this) {
         return false;
      } else {
         IBlockState connectBlockState = worldIn.getBlockState(pos.offset(dirFromNewBlock));
         EnumFacing connectBlockFacing = connectBlockState.getValue(FACING);
         return this.strictSideConnection?newBlockFacing == connectBlockFacing:newBlockFacing == connectBlockFacing || newBlockFacing == connectBlockFacing.getOpposite();
      }
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
      LEFT = PropertyBool.create("left");
      RIGHT = PropertyBool.create("right");
   }
}
