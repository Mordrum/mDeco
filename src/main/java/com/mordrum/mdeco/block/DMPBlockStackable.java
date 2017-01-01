package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockStackable extends DMPBlockDirectional {
   public static final PropertyInteger POSITION = PropertyInteger.create("position", 0, 3);

   public DMPBlockStackable(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POSITION, Integer.valueOf(0)));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING, POSITION});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
      Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
      super.getActualState(state, worldIn, pos);
      IBlockState newState;
      if(blockBelow == this) {
         if(blockAbove == this) {
            newState = state.withProperty(POSITION, Integer.valueOf(3));
         } else {
            newState = state.withProperty(POSITION, Integer.valueOf(2));
         }
      } else if(blockAbove == this) {
         newState = state.withProperty(POSITION, Integer.valueOf(1));
      } else {
         newState = state.withProperty(POSITION, Integer.valueOf(0));
      }

      return newState;
   }
}
