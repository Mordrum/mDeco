package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemSupportPole;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockSupportPole extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.21875D, 0.625D);
   protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.21875D, 1.0D);

   public DMPBlockSupportPole(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemSupportPole.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      EnumFacing facing = state.getValue(FACING);
      if(worldIn.isAirBlock(pos.offset(facing)) || !worldIn.isSideSolid(pos.offset(facing), facing.getOpposite())) {
         this.dropBlockAsItem(worldIn, pos, state, 1);
         worldIn.setBlockToAir(pos);
      }

   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumFacing facing = state.getValue(FACING);
      return facing != EnumFacing.EAST && facing != EnumFacing.WEST?AABB_NS:AABB_EW;
   }
}
