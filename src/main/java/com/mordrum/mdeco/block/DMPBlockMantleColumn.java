package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemMantleColumn;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockMantleColumn extends DMPBlockStackable {
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.375D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.25D, 0.0D, 0.625D, 0.75D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.625D, 1.0D, 0.75D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.625D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D);

   public DMPBlockMantleColumn(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemMantleColumn.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return false;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return 0;
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
