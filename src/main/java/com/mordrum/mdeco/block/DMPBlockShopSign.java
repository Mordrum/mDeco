package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemShopSign;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityShopSign;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockShopSign extends DMPBlockDirectional implements ITileEntityProvider {
   protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D);
   protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D);

   public DMPBlockShopSign(DMPDecoration decoration) {
      super(decoration);
      this.isBlockContainer = true;
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemShopSign.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if(tileentity instanceof IInventory) {
         InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
         worldIn.updateComparatorOutputLevel(pos, this);
      }

      worldIn.removeTileEntity(pos);
      super.breakBlock(worldIn, pos, state);
   }

//   TODO investigate
//   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
//      super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
//      TileEntity tileentity = worldIn.getTileEntity(pos);
//      return tileentity == null?false:tileentity.receiveClientEvent(eventID, eventParam);
//   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityShopSign();
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(worldIn.isRemote) {
         return true;
      } else {
         playerIn.openGui(MDeco.instance, 24, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   @Override
   public void onNeighborChange(IBlockAccess iBlockAccess, BlockPos pos, BlockPos neighbor) {
      World world = (World) iBlockAccess;
      super.onNeighborChange(world, pos, neighbor);

      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityShopSign) {
         tileentity.updateContainingBlockInfo();
      }

      if(!world.isSideSolid(pos.up(), EnumFacing.DOWN, false) && !(world.getBlockState(pos.up()).getBlock() instanceof DMPBlockSupportPole)) {
         this.dropBlockAsItem(world, pos, world.getBlockState(pos), 1);
         world.setBlockToAir(pos);
      }
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      return facing != EnumFacing.EAST && facing != EnumFacing.WEST?AABB_NS:AABB_EW;
   }
}
