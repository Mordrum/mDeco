package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemCurtainWool;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockCurtain extends DMPBlockDirectional {
   public static final PropertyBool BOTTOM = PropertyBool.create("bottom");
   protected static final AxisAlignedBB AABB_BOTTOM_N = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.25D);
   protected static final AxisAlignedBB AABB_BOTTOM_S = new AxisAlignedBB(0.0D, 0.5D, 0.75D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_BOTTOM_E = new AxisAlignedBB(0.75D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_BOTTOM_W = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.25D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_TOP_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
   protected static final AxisAlignedBB AABB_TOP_S = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_TOP_E = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_TOP_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);

   public DMPBlockCurtain(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(BOTTOM, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH));
      this.useNeighborBrightness = true;
      this.blockSoundType = SoundType.CLOTH;
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemCurtainWool.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{BOTTOM, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(BOTTOM, Boolean.valueOf(this.isBottomCurtainBlock(worldIn, pos)));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      if(!worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)) {
         return false;
      } else {
         Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
         return blockAbove == null?false:blockAbove instanceof DMPBlockCurtain || blockAbove instanceof DMPBlockCurtainRod;
      }
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
      if(!(blockAbove instanceof DMPBlockCurtain) && !(blockAbove instanceof DMPBlockCurtainRod)) {
         this.dropBlockAsItem(worldIn, pos, state, 1);
         worldIn.setBlockToAir(pos);
      }

   }

   protected boolean isBottomCurtainBlock(IBlockAccess worldIn, BlockPos pos) {
      Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
      return blockBelow == null?false:!(blockBelow instanceof DMPBlockCurtain);
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      boolean bottom = ((Boolean)state.getValue(BOTTOM)).booleanValue();
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         return bottom?AABB_BOTTOM_N:AABB_TOP_N;
      case 2:
         return bottom?AABB_BOTTOM_S:AABB_TOP_S;
      case 3:
         return bottom?AABB_BOTTOM_W:AABB_TOP_W;
      case 4:
         return bottom?AABB_BOTTOM_E:AABB_TOP_E;
      default:
         return FULL_BLOCK_AABB;
      }
   }
}
