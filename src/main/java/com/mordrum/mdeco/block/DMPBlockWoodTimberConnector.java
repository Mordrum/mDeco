package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemWoodTimberConnector;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockWoodTimberConnector extends DMPBlockConnectAll {
   public DMPBlockWoodTimberConnector(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWoodTimberConnector.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
      this.collisionPixelSize = 4;
      this.hitboxPixelSize = 4;
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side.getOpposite()));
      Block block = stateTarget.getBlock();
      return block == null?false:(!(block instanceof BlockGlass) && !(block instanceof BlockGlowstone) && !(block instanceof BlockStainedGlass)?((block instanceof BlockWall || block instanceof BlockFence) && (side == EnumFacing.DOWN || side == EnumFacing.UP)?true:block.isSideSolid(stateTarget, worldIn, pos.offset(side.getOpposite()), side)):true);
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side));
      Block block = stateTarget.getBlock();
      return block == null?false:(!(block instanceof BlockGlass) && !(block instanceof BlockGlowstone) && !(block instanceof BlockStainedGlass)?(!(block instanceof BlockWall) && !(block instanceof BlockFence) || side != EnumFacing.DOWN && side != EnumFacing.UP?block.isSideSolid(stateTarget, worldIn, pos.offset(side), side.getOpposite()):true):true);
   }
}
