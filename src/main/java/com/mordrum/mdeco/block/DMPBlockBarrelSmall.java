package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemBarrelSmall;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockBarrelSmall extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.125D, 0.0D, 0.234375D, 0.875D, 0.609375D, 0.765625D);
   protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.234375D, 0.0D, 0.125D, 0.765625D, 0.609375D, 0.875D);

   public DMPBlockBarrelSmall(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemBarrelSmall.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN || side == EnumFacing.UP;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumFacing facing = state.getValue(FACING);
      return facing != EnumFacing.EAST && facing != EnumFacing.WEST?AABB_NS:AABB_EW;
   }
}
