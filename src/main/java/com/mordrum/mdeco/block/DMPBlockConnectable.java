package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockConnectable extends DMPBlockBaseDecoration {
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");

   public DMPBlockConnectable(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(EAST, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{EAST, NORTH, WEST, SOUTH});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos, EnumFacing.EAST))).withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos, EnumFacing.NORTH))).withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos, EnumFacing.SOUTH))).withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos, EnumFacing.WEST)));
   }

   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side));
      return stateTarget.getBlock().isSideSolid(stateTarget, worldIn, pos.offset(side), side.getOpposite());
   }
}
