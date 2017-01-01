package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.inventory.DMPContainerWorkbenchFoundry;
import com.mordrum.mdeco.item.DMPItemWorkbenchFoundry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockWorkbenchFoundry extends Block {
   public static final PropertyDirection FACING;

   public DMPBlockWorkbenchFoundry(String unlocalizedName) {
      super(Material.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setUnlocalizedName(unlocalizedName);
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.setTickRandomly(false);
      this.setHardness(2.5F);
      this.setSoundType(SoundType.WOOD);
      this.enableStats = false;
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWorkbenchFoundry.class, unlocalizedName);
      this.registerOreDictName(unlocalizedName);
   }

   protected void registerOreDictName(String name) {
      OreDictionary.registerOre(name, this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(FACING).getHorizontalIndex();
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
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
         playerIn.openGui(MDeco.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
      if(MDeco.settings.workbenchFoundryParticles) {
         EnumFacing facing = state.getValue(FACING);
         double pixel = 0.0625D;
         double d0 = 0.0D;
         double d1 = (double)pos.getY() + 1.0D + pixel * 9.0D;
         double d2 = 0.0D;
         if(facing == EnumFacing.NORTH) {
            d0 = (double)pos.getX() + pixel * 5.0D;
            d2 = (double)pos.getZ() + pixel * 4.5D;
         }

         if(facing == EnumFacing.SOUTH) {
            d0 = (double)pos.getX() + pixel * 11.0D;
            d2 = (double)pos.getZ() + pixel * 11.5D;
         }

         if(facing == EnumFacing.WEST) {
            d0 = (double)pos.getX() + pixel * 4.5D;
            d2 = (double)pos.getZ() + pixel * 11.5D;
         }

         if(facing == EnumFacing.EAST) {
            d0 = (double)pos.getX() + pixel * 11.0D;
            d2 = (double)pos.getZ() + pixel * 4.5D;
         }

         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
      }
   }

   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
   }

   public static class InterfaceFoundryTable implements IInteractionObject {
      private final World world;
      private final BlockPos position;

      public InterfaceFoundryTable(World worldIn, BlockPos pos) {
         this.world = worldIn;
         this.position = pos;
      }

      public String getName() {
         return null;
      }

      public boolean hasCustomName() {
         return false;
      }

      public ITextComponent getDisplayName() {
         return new TextComponentTranslation(MDeco.blocks.workbenchFoundry.getUnlocalizedName() + ".name");
      }

      public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
         return new DMPContainerWorkbenchFoundry(this.world, this.position, playerIn);
      }

      public String getGuiID() {
         return "mdeco:workbenchFoundry";
      }
   }
}
