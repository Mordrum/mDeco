package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.tileentity.DMPTileEntityStorage;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPTab;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public abstract class DMPBlockStorage extends BlockContainer {
	public static final PropertyDirection FACING;
	protected DMPDecoration decoration;

	public DMPBlockStorage(DMPDecoration decoration) {
		super(decoration.material);
		this.decoration = decoration;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setUnlocalizedName(this.decoration.name());
		this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.decorations));
		this.setTickRandomly(false);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.WOOD);
		this.enableStats = false;
	}

	protected void registerOreDictName(String name) {
		OreDictionary.registerOre(name, this);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return this.decoration.material == Material.CLOTH || this.decoration.material == Material.WOOD;
	}

	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return this.decoration.material != Material.CLOTH && this.decoration.material != Material.WOOD ? 0 : 20;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		super.onNeighborChange(world, pos, neighbor);
		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof DMPTileEntityStorage) {
			tileentity.updateContainingBlockInfo();
		}

	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
			if (ilockablecontainer != null) {
				playerIn.displayGUIChest(ilockablecontainer);
			}
		}

		return true;
	}

	protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return !(tileentity instanceof DMPTileEntityStorage) ? null :
				(this.isBlocked(worldIn, pos) ? null : (DMPTileEntityStorage) tileentity);
	}

	protected boolean isBlocked(World worldIn, BlockPos pos) {
		return false;
	}

	static {
		FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
	}
}
