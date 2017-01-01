package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemPole;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockWall;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockPole extends DMPBlockBaseDecoration {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 3);
   public static final PropertyInteger ORIENTATION = PropertyInteger.create("orientation", 0, 2);
   protected static final AxisAlignedBB AABB_X = new AxisAlignedBB(0.0D, 0.3125D, 0.3125D, 1.0D, 0.6875D, 0.6875D);
   protected static final AxisAlignedBB AABB_Y = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
   protected static final AxisAlignedBB AABB_Z = new AxisAlignedBB(0.3125D, 0.3125D, 0.0D, 0.6875D, 0.6875D, 1.0D);

   public DMPBlockPole(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, 3).withProperty(ORIENTATION, 1));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemPole.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, CONNECTED, ORIENTATION);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      int orientation = state.getValue(ORIENTATION);
      IBlockState blockNorth;
      IBlockState blockSouth;
      if(orientation == 0) {
         blockNorth = worldIn.getBlockState(pos.west());
         blockSouth = worldIn.getBlockState(pos.east());
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockPole &&
		         blockNorth.getValue(ORIENTATION) == orientation?(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?state.withProperty(CONNECTED, 0):state.withProperty(CONNECTED, 1)):(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?state.withProperty(CONNECTED, 2):state.withProperty(CONNECTED, 3))):state;
      } else if(orientation == 1) {
         blockNorth = worldIn.getBlockState(pos.up());
         blockSouth = worldIn.getBlockState(pos.down());
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockPole &&
		         blockNorth.getValue(ORIENTATION) == orientation?(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?state.withProperty(CONNECTED, 0):state.withProperty(CONNECTED, 1)):(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?this.getDefaultState().withProperty(CONNECTED, 2):this.getDefaultState().withProperty(CONNECTED, 3))):worldIn.getBlockState(pos);
      } else if(orientation == 2) {
         blockNorth = worldIn.getBlockState(pos.north());
         blockSouth = worldIn.getBlockState(pos.south());
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockPole &&
		         blockNorth.getValue(ORIENTATION) == orientation?(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?state.withProperty(CONNECTED, 0):state.withProperty(CONNECTED, 1)):(blockSouth.getBlock() instanceof DMPBlockPole &&
		         blockSouth.getValue(ORIENTATION) == orientation?state.withProperty(CONNECTED, 2):state.withProperty(CONNECTED, 3))):state;
      } else {
         return state;
      }
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(ORIENTATION);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(ORIENTATION, meta);
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side.getOpposite()));
      Block block = stateTarget.getBlock();
      return block != null && (!(!(block instanceof BlockGlass) && !(block instanceof BlockGlowstone) &&
		      !(block instanceof BlockStainedGlass)) || (
		      !(!(block instanceof BlockWall) && !(block instanceof BlockFence) ||
				      side != EnumFacing.DOWN && side != EnumFacing.UP) ||
				      block.isSideSolid(stateTarget, worldIn, pos.offset(side.getOpposite()), side)));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      int orientation = base_state.getValue(ORIENTATION);
      return orientation == 0?side == EnumFacing.WEST || side == EnumFacing.EAST:(orientation == 1?side == EnumFacing.UP || side == EnumFacing.DOWN:(
		      orientation == 2 && (side == EnumFacing.NORTH || side == EnumFacing.SOUTH)));
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      int orientation = state.getValue(ORIENTATION);
      return orientation == 0?AABB_X:(orientation == 1?AABB_Y:(orientation == 2?AABB_Z:Block.FULL_BLOCK_AABB));
   }
}
