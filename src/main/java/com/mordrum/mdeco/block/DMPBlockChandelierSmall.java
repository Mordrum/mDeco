package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemChandelierSmall;
import com.mordrum.mdeco.object.DMPDecoration;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockChandelierSmall extends DMPBlockBaseDecoration {
   public DMPBlockChandelierSmall(DMPDecoration decoration) {
      super(decoration);
      this.setTickRandomly(true);
      this.setLightLevel(1.0F);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemChandelierSmall.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
      float pixel = 0.0625F;
      double x = (double)pos.getX();
      double y = (double)pos.getY() + 0.55D;
      double z = (double)pos.getZ();
      this.spawnCandleParticles(worldIn, x + (double)(pixel * 8.0F), y, z - (double)(pixel * 2.0F));
      this.spawnCandleParticles(worldIn, x + (double)(pixel * 18.0F), y, z + (double)(pixel * 8.0F));
      this.spawnCandleParticles(worldIn, x + (double)(pixel * 8.0F), y, z + (double)(pixel * 18.0F));
      this.spawnCandleParticles(worldIn, x - (double)(pixel * 2.0F), y, z + (double)(pixel * 8.0F));
   }

   private void spawnCandleParticles(World worldIn, double x, double y, double z) {
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return this.canBlockStay(worldIn, pos);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP;
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return this.canBlockStay(worldIn, pos);
   }

   private boolean canBlockStay(World worldIn, BlockPos pos) {
      return worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, true);
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      if(!this.canBlockStay(worldIn, pos)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }
}
