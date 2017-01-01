package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemChair;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockChair extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 1.0D, 0.625D, 0.9375D);
   protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.625D, 1.0D);

   public DMPBlockChair(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemChair.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumFacing facing = state.getValue(FACING);
      return facing != EnumFacing.NORTH && facing != EnumFacing.SOUTH?AABB_EW:AABB_NS;
   }
}
