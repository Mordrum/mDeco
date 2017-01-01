package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemDeskHutchWoodBasic;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockDeskHutchWoodBasic extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(-0.5625D, 0.0D, 0.0D, 1.4375D, 1.0D, 0.5D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(-0.5625D, 0.0D, 0.5D, 1.4375D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, -0.5625D, 0.5D, 1.0D, 1.4375D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.5D, 0.0D, -0.5625D, 1.0D, 1.0D, 1.4375D);

   public DMPBlockDeskHutchWoodBasic(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemDeskHutchWoodBasic.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
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
