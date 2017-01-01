package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemWoodTimber;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockWall;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPBlockWoodTimber extends DMPBlockBaseDecoration {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 3);
   public static final PropertyInteger ORIENTATION = PropertyInteger.create("orientation", 0, 2);
   protected static final AxisAlignedBB AABB_0 = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
   protected static final AxisAlignedBB AABB_1 = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
   protected static final AxisAlignedBB AABB_2 = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);

   public DMPBlockWoodTimber(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, 3).withProperty(ORIENTATION, 1));
      this.setRegistryName(this.decoration.name());
      GameRegistry.register(this);
      GameRegistry.register(new DMPItemWoodTimber(this).setRegistryName(this.decoration.name()));
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
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockWoodTimber && blockNorth.getValue(ORIENTATION)
                 .intValue() == orientation?(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?state.withProperty(CONNECTED, Integer.valueOf(0)):state.withProperty(CONNECTED, Integer.valueOf(1))):(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?state.withProperty(CONNECTED, Integer.valueOf(2)):state.withProperty(CONNECTED, Integer.valueOf(3)))):state;
      } else if(orientation == 1) {
         blockNorth = worldIn.getBlockState(pos.up());
         blockSouth = worldIn.getBlockState(pos.down());
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockWoodTimber && blockNorth.getValue(ORIENTATION)
                 .intValue() == orientation?(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?state.withProperty(CONNECTED, Integer.valueOf(0)):state.withProperty(CONNECTED, Integer.valueOf(1))):(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(2)):this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(3)))):worldIn.getBlockState(pos);
      } else if(orientation == 2) {
         blockNorth = worldIn.getBlockState(pos.north());
         blockSouth = worldIn.getBlockState(pos.south());
         return blockNorth != null && blockSouth != null?(blockNorth.getBlock() instanceof DMPBlockWoodTimber && blockNorth.getValue(ORIENTATION)
                 .intValue() == orientation?(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?state.withProperty(CONNECTED, Integer.valueOf(0)):state.withProperty(CONNECTED, Integer.valueOf(1))):(blockSouth.getBlock() instanceof DMPBlockWoodTimber && blockSouth.getValue(ORIENTATION)
                 .intValue() == orientation?state.withProperty(CONNECTED, Integer.valueOf(2)):state.withProperty(CONNECTED, Integer.valueOf(3)))):state;
      } else {
         return state;
      }
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(ORIENTATION).intValue();
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(ORIENTATION, Integer.valueOf(meta));
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side.getOpposite()));
      Block block = stateTarget.getBlock();
      return block != null && (!(!(block instanceof BlockGlass) && !(block instanceof BlockGlowstone) &&
              !(block instanceof BlockStainedGlass)) || (
              (block instanceof BlockWall || block instanceof BlockFence) &&
                      (side == EnumFacing.DOWN || side == EnumFacing.UP) || (
                      block.isSideSolid(stateTarget, worldIn, pos.offset(side.getOpposite()), side) ||
                              block.getMaterial(stateTarget).isSolid())));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      int orientation = base_state.getValue(ORIENTATION).intValue();
      return orientation == 0?side == EnumFacing.WEST || side == EnumFacing.EAST:(orientation == 1?side == EnumFacing.UP || side == EnumFacing.DOWN:(
              orientation == 2 && (side == EnumFacing.NORTH || side == EnumFacing.SOUTH)));
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch(state.getValue(ORIENTATION).intValue()) {
      case 0:
         return AABB_0;
      case 1:
         return AABB_1;
      case 2:
         return AABB_2;
      default:
         return Block.FULL_BLOCK_AABB;
      }
   }
}
