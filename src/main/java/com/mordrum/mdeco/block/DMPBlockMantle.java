package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemMantle;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityMantle;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class DMPBlockMantle extends DMPBlockStorage implements ITileEntityProvider {
   public static final PropertyInteger POSITION = PropertyInteger.create("position", 0, 7);
   protected static final AxisAlignedBB AABB_TOP_N = new AxisAlignedBB(0.0D, 0.3125D, 0.0D, 1.0D, 0.4375D, 0.4375D);
   protected static final AxisAlignedBB AABB_TOP_S = new AxisAlignedBB(0.0D, 0.3125D, 0.5625D, 1.0D, 0.4375D, 1.0D);
   protected static final AxisAlignedBB AABB_TOP_W = new AxisAlignedBB(0.0D, 0.3125D, 0.0D, 0.4375D, 0.4375D, 1.0D);
   protected static final AxisAlignedBB AABB_TOP_E = new AxisAlignedBB(0.5625D, 0.3125D, 0.0D, 1.0D, 0.4375D, 1.0D);
   protected static final AxisAlignedBB AABB_BOTTOM_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 0.4375D);
   protected static final AxisAlignedBB AABB_BOTTOM_S = new AxisAlignedBB(0.0D, 0.0D, 0.5625D, 1.0D, 0.4375D, 1.0D);
   protected static final AxisAlignedBB AABB_BOTTOM_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.4375D, 0.4375D, 1.0D);
   protected static final AxisAlignedBB AABB_BOTTOM_E = new AxisAlignedBB(0.5625D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D);

   public DMPBlockMantle(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POSITION, 0));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemMantle.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityMantle)?null:(DMPTileEntityMantle)tileentity;
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING, POSITION);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      Block blockLeft = null;
      Block blockRight = null;
      Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
      EnumFacing facing = state.getValue(FACING);
      switch(facing.ordinal()) {
      case 1:
         blockLeft = worldIn.getBlockState(pos.west()).getBlock();
         blockRight = worldIn.getBlockState(pos.east()).getBlock();
      case 2:
         blockLeft = worldIn.getBlockState(pos.east()).getBlock();
         blockRight = worldIn.getBlockState(pos.west()).getBlock();
      case 3:
         blockLeft = worldIn.getBlockState(pos.south()).getBlock();
         blockRight = worldIn.getBlockState(pos.north()).getBlock();
      case 4:
         blockLeft = worldIn.getBlockState(pos.north()).getBlock();
         blockRight = worldIn.getBlockState(pos.south()).getBlock();
      default:
         boolean position = false;
         int position1;
         if(blockLeft == this) {
            position1 = 2;
         } else if(blockRight == this) {
            position1 = 1;
         } else {
            position1 = 0;
         }

         if(blockLeft == this && blockRight == this) {
            position1 = 3;
         }

         if(!(blockBelow instanceof DMPBlockMantleColumn)) {
            position1 += 4;
         }

         return super.getActualState(state, worldIn, pos).withProperty(POSITION, position1);
      }
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityMantle();
   }

   @Override public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityMantle) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(worldIn.isRemote) {
         return true;
      } else {
         playerIn.openGui(MDeco.instance, 19, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }

   public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return false;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return 0;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      boolean bottom = source.getBlockState(pos.down()).getBlock() instanceof DMPBlockMantleColumn;
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
         return Block.FULL_BLOCK_AABB;
      }
   }
}
