package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockBaseDecoration extends Block {
   protected DMPDecoration decoration;
   protected static final AxisAlignedBB AABB_NONE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

   public DMPBlockBaseDecoration(DMPDecoration decoration) {
      super(decoration.material);
      this.decoration = decoration;
      this.setDefaultState(this.blockState.getBaseState());
      this.setUnlocalizedName(this.decoration.name());
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.decorations));
      this.setTickRandomly(false);
      if(this.decoration.material == Material.IRON) {
         this.setHardness(2.0F);
         this.setResistance(8.0F);
         this.setSoundType(SoundType.METAL);
      } else if(this.decoration.material == Material.ROCK) {
         this.setHardness(2.0F);
         this.setResistance(6.5F);
         this.setSoundType(SoundType.STONE);
      } else if(this.decoration.material == Material.WOOD) {
         this.setHardness(2.0F);
         this.setResistance(5.0F);
         this.setSoundType(SoundType.WOOD);
      } else if(this.decoration.material == Material.CLAY) {
         this.setHardness(0.8F);
         this.setResistance(4.0F);
         this.setSoundType(SoundType.STONE);
      } else if(this.decoration.material == Material.CLOTH) {
         this.setHardness(0.6F);
         this.setResistance(1.0F);
         this.setSoundType(SoundType.CLOTH);
      } else {
         this.setHardness(1.0F);
         this.setResistance(5.0F);
         this.setSoundType(SoundType.STONE);
      }

      this.enableStats = false;
   }

   protected void registerOreDictName(String name) {
      OreDictionary.registerOre(name, this);
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

   public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return this.decoration.material == Material.CLOTH || this.decoration.material == Material.WOOD;
   }

   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
      return this.decoration.material != Material.CLOTH && this.decoration.material != Material.WOOD?0:20;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public DMPDecorationType getDecorationType() {
      return this.decoration.decorationType;
   }

   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }
}
