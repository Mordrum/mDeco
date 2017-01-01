package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemBenchWoodMetalArm;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockBenchWoodMetalArm extends DMPBlockConnectSides {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);

   public DMPBlockBenchWoodMetalArm(DMPDecoration decoration) {
      super(decoration);
      this.strictSideConnection = true;
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemBenchWoodMetalArm.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
