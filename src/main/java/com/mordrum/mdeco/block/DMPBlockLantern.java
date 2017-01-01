package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemLantern;
import com.mordrum.mdeco.object.DMPDecoration;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockLantern extends DMPBlockConnectable {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);

   public DMPBlockLantern(DMPDecoration decoration) {
      super(decoration);
      this.setTickRandomly(true);
      this.setLightLevel(1.0F);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemLantern.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(EAST, this.canConnectTo(worldIn, pos, EnumFacing.EAST, true)).withProperty(NORTH, this
		      .canConnectTo(worldIn, pos, EnumFacing.NORTH, true)).withProperty(SOUTH, this
		      .canConnectTo(worldIn, pos, EnumFacing.SOUTH, true)).withProperty(WEST, this
		      .canConnectTo(worldIn, pos, EnumFacing.WEST, true));
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
      double d0 = (double)pos.getX() + 0.5D;
      double d1 = (double)pos.getY() + 0.55D;
      double d2 = (double)pos.getZ() + 0.5D;
      worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer getBlockLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return this.canBlockStay(worldIn, pos);
   }

   private boolean canBlockStay(World worldIn, BlockPos pos) {
      if(!this.canConnectTo(worldIn, pos, EnumFacing.DOWN, false) && !this.canConnectTo(worldIn, pos, EnumFacing.EAST, false) && !this.canConnectTo(worldIn, pos, EnumFacing.NORTH, false) && !this.canConnectTo(worldIn, pos, EnumFacing.SOUTH, false) && !this.canConnectTo(worldIn, pos, EnumFacing.UP, false) && !this.canConnectTo(worldIn, pos, EnumFacing.WEST, false)) {
         IBlockState stateTarget = worldIn.getBlockState(pos.up());
         Block block = stateTarget.getBlock();
         if((block == null || !(block instanceof DMPBlockChain) && !(block instanceof DMPBlockLantern)) && !block.isSideSolid(stateTarget, worldIn, pos.up(), EnumFacing.DOWN)) {
            stateTarget = worldIn.getBlockState(pos.down());
            block = worldIn.getBlockState(pos.down()).getBlock();
            return block != null && (block.isSideSolid(stateTarget, worldIn, pos.down(), EnumFacing.UP) || block instanceof BlockFence || block instanceof BlockWall);
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      Block block = state.getBlock();
      if(block != null && block == this) {
         if(!this.canBlockStay(worldIn, pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

      }
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing side, boolean includeNonPoleBlocks) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side));
      Block block = stateTarget.getBlock();
      return (side == EnumFacing.DOWN || side == EnumFacing.UP) && block instanceof BlockFence || (
		      (block != null && block.isSideSolid(stateTarget, worldIn, pos.offset(side), side.getOpposite())) && (
				      !(!(block instanceof DMPBlockPole) && !(block instanceof DMPBlockPoleConnector)) ||
						      includeNonPoleBlocks && block instanceof DMPBlockCap));
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
