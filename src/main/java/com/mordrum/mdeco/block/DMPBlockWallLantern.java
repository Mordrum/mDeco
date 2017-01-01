package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemWallLantern;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityWallLantern;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockWallLantern extends DMPBlockStorage implements ITileEntityProvider {
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.28125D, 0.0D, 0.0D, 0.71875D, 1.0D, 0.71875D);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.28125D, 0.0D, 0.28125D, 0.71875D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.28125D, 0.71875D, 1.0D, 0.71875D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.28125D, 0.0D, 0.28125D, 1.0D, 1.0D, 0.71875D);

	public DMPBlockWallLantern(DMPDecoration decoration) {
		super(decoration);
		this.setTickRandomly(true);
		this.setLightLevel(1.0F);
		this.useNeighborBrightness = true;
		com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWallLantern.class, this.decoration.name());
		this.registerOreDictName(this.decoration.oreDictName);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return !(tileentity instanceof DMPTileEntityWallLantern) ? null :
				(this.isBlocked(worldIn, pos) ? null : (DMPTileEntityWallLantern) tileentity);
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new DMPTileEntityWallLantern();
	}

	@Override
	public void onNeighborChange(IBlockAccess iBlockAccess, BlockPos pos, BlockPos neighbor) {
		World world = (World) iBlockAccess;
		super.onNeighborChange(world, pos, neighbor);
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof DMPTileEntityWallLantern) {
			tileentity.updateContainingBlockInfo();
		}

		IBlockState state = world.getBlockState(pos);
		EnumFacing facing = state.getValue(FACING);
		if (world.isAirBlock(pos.offset(facing)) || !world.isSideSolid(pos.offset(facing), facing.getOpposite())) {
			this.dropBlockAsItem(world, pos, state, 1);
			world.setBlockToAir(pos);
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.3D;
		double d2 = (double) pos.getZ() + 0.5D;
		worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			playerIn.openGui(MDeco.instance, 26, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}

	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	protected boolean canBlockStay(World worldIn, BlockPos pos) {
		EnumFacing facing = worldIn.getBlockState(pos).getValue(FACING);
		return worldIn.isSideSolid(pos.offset(facing), facing.getOpposite());
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING).ordinal()) {
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
