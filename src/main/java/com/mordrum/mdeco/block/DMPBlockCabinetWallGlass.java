package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemCabinetWallGlass;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetWallGlass;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockCabinetWallGlass extends DMPBlockStorage implements ITileEntityProvider {
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.6875D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

   public DMPBlockCabinetWallGlass(DMPDecoration decoration) {
      super(decoration);
      this.setRegistryName(this.decoration.name());
      GameRegistry.register(this);
      GameRegistry.register(new DMPItemCabinetWallGlass(this).setRegistryName(this.decoration.name()));
      this.registerOreDictName(this.decoration.oreDictName);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer getBlockLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityCabinetWallGlass)?null:(this.isBlocked(worldIn, pos)?null:(DMPTileEntityCabinetWallGlass)tileentity);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(FACING).getHorizontalIndex();
   }

   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean isFullCube(IBlockState state) {
      return false;
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityCabinetWallGlass();
   }


   @Override
   public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityCabinetWallGlass) {
         tileentity.updateContainingBlockInfo();
      }

   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(worldIn.isRemote) {
         return true;
      } else {
         playerIn.openGui(MDeco.instance, 13, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
      EnumFacing facing = base_state.getValue(FACING);
      return side == facing;
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
         return Block.FULL_BLOCK_AABB;
      }
   }
}
