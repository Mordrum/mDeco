package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemWorkbenchSaw;
import com.mordrum.mdeco.tileentity.DMPTileEntityWorkbenchSaw;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockWorkbenchSaw extends BlockContainer {
   public static final PropertyBool ACTIVE = PropertyBool.create("active");
   public static final PropertyDirection FACING;

   public DMPBlockWorkbenchSaw(String unlocalizedName) {
      super(Material.WOOD);
      this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH));
      this.setUnlocalizedName(unlocalizedName);
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.setTickRandomly(false);
      this.setHardness(2.5F);
      this.setSoundType(SoundType.WOOD);
      this.enableStats = false;
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWorkbenchSaw.class, unlocalizedName);
      this.registerOreDictName(unlocalizedName);
   }

   protected void registerOreDictName(String name) {
      OreDictionary.registerOre(name, this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{ACTIVE, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      byte b0 = 0;
      int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      boolean active = ((Boolean)state.getValue(ACTIVE)).booleanValue();
      i |= (active?1:0) << 2;
      return i;
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.getHorizontal(meta &= -13);
      return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ACTIVE, Boolean.valueOf((meta | 12) >> 2 != 0));
   }

   public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
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

   public boolean isFlammable(IBlockAccess worldIn, BlockPos pos, EnumFacing face) {
      return false;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(worldIn.isRemote) {
         return true;
      } else {
         TileEntity tileEntity = worldIn.getTileEntity(pos);
         if(tileEntity instanceof DMPTileEntityWorkbenchSaw) {
            playerIn.openGui(MDeco.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
         }

         return true;
      }
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityWorkbenchSaw();
   }

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = worldIn.getTileEntity(pos);
      if(tileEntity instanceof DMPTileEntityWorkbenchSaw) {
         InventoryHelper.dropInventoryItems(worldIn, pos, (DMPTileEntityWorkbenchSaw)tileEntity);
      }

      super.breakBlock(worldIn, pos, state);
   }

   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
   }
}
