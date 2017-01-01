package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockConnectableDirectional extends DMPBlockBaseDecoration {
   public static final PropertyInteger ORIENTATION = PropertyInteger.create("orientation", 0, 1);
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");

   public DMPBlockConnectableDirectional(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(ORIENTATION, 0).withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, ORIENTATION, NORTH, EAST, WEST, SOUTH);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      IBlockState actualState = worldIn.getBlockState(pos);
      actualState = actualState.withProperty(NORTH, this.canConnectTo(worldIn, pos.north())).withProperty(EAST, this
		      .canConnectTo(worldIn, pos.east())).withProperty(SOUTH, this
		      .canConnectTo(worldIn, pos.south())).withProperty(WEST, this
		      .canConnectTo(worldIn, pos.west()));
      return actualState;
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(ORIENTATION);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(ORIENTATION, meta);
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
      Block block = worldIn.getBlockState(pos).getBlock();
      return block == this;
   }
}
