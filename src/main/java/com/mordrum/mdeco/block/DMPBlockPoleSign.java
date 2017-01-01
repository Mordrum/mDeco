package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemPoleSign;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import net.minecraft.block.Block;
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

import javax.annotation.Nullable;

public class DMPBlockPoleSign extends DMPBlockDirectional implements ITileEntityProvider {
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.4375D, 0.3125D, 0.0D, 0.5625D, 0.6875D, 1.0D);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.4375D, 0.3125D, 0.0D, 0.5625D, 0.6875D, 1.0D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.3125D, 0.4375D, 1.0D, 0.6875D, 0.5625D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.0D, 0.3125D, 0.4375D, 1.0D, 0.6875D, 0.5625D);

	public DMPBlockPoleSign(DMPDecoration decoration) {
		super(decoration);
		com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemPoleSign.class, this.decoration.name());
		this.registerOreDictName(this.decoration.oreDictName);
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
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
		return new DMPTileEntityPoleSign();
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			playerIn.openGui(MDeco.instance, 22, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}

	@Override public void onNeighborChange(IBlockAccess iBlockAccess, BlockPos pos, BlockPos neighbor) {
		World world = (World) iBlockAccess;
		super.onNeighborChange(world, pos, neighbor);
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof DMPTileEntityPoleSign) {
			tileentity.updateContainingBlockInfo();
		}
		IBlockState state = world.getBlockState(pos);

		if (!canBlockStay(world, pos, state.getValue(FACING))) {
			this.dropBlockAsItem(world, pos, state, 1);
			world.setBlockToAir(pos);
		}
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
		return super.canConnectRedstone(state, world, pos, side);
	}

	public static boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing) {
		BlockPos posTarget = pos.offset(facing);
		if (!(worldIn.getBlockState(posTarget).getBlock() instanceof DMPBlockBaseDecoration)) {
			return false;
		} else {
			DMPDecorationType decorationType = ((DMPBlockBaseDecoration) worldIn.getBlockState(posTarget)
					.getBlock()).getDecorationType();
			if (decorationType == DMPDecorationType.poleMetal) {
				if (worldIn.getBlockState(posTarget).getValue(DMPBlockPole.ORIENTATION) == 1) {
					return true;
				}
			} else if (decorationType == DMPDecorationType.poleMetalConnector) {
				DMPBlockPoleConnector block = (DMPBlockPoleConnector) worldIn.getBlockState(posTarget).getBlock();
				if (block == null) {
					return false;
				}

				IBlockState actualState = block.getActualState(worldIn.getBlockState(posTarget), worldIn, posTarget);
				if (actualState.getValue(DMPBlockPoleConnector.DOWN) &&
						actualState.getValue(DMPBlockPoleConnector.UP)) {
					return true;
				}
			}

			return false;
		}
	}

	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(FACING);
		return facing == EnumFacing.NORTH ? AABB_N : (facing == EnumFacing.SOUTH ? AABB_S :
				(facing == EnumFacing.WEST ? AABB_W : (facing == EnumFacing.EAST ? AABB_E : Block.FULL_BLOCK_AABB)));
	}
}
