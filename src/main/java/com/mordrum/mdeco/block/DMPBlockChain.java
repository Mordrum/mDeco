package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.item.DMPItemChain;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockChain extends DMPBlockBaseDecoration {
   protected static final AxisAlignedBB AABB_LARGE = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
   protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

   public DMPBlockChain(DMPDecoration decoration) {
      super(decoration);
      this.setTickRandomly(false);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemChain.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side.getOpposite()));
      return side != EnumFacing.DOWN && side != EnumFacing.UP?false:stateTarget.getBlock().isSideSolid(stateTarget, worldIn, pos.offset(side.getOpposite()), side.getOpposite());
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN || side == EnumFacing.UP;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return this.canBlockStay(worldIn, pos);
   }

   private boolean canBlockStay(World worldIn, BlockPos pos) {
      Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
      return blockAbove == null?false:worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, true);
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      if(!this.canBlockStay(worldIn, pos)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return this.decoration.decorationType == DMPDecorationType.chain?AABB_SMALL:AABB_LARGE;
   }
}
