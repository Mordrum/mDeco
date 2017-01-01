package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemFountain;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.object.DMPDecoration;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockFountain extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);

   public DMPBlockFountain(DMPDecoration decoration) {
      super(decoration);
      this.setTickRandomly(true);
      if(decoration.decorationType == DMPDecorationType.fountainLava) {
         this.setLightLevel(0.75F);
      }

      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemFountain.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN;
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
      if(this.decoration.decorationType == DMPDecorationType.fountainLava) {
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 1.0F), (double)((float)pos.getZ() + 0.5F), 0.0D, 0.0D, 0.0D);
      } else if(this.decoration.decorationType == DMPDecorationType.fountainWater) {
         worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 1.0F), (double)((float)pos.getZ() + 0.5F), 0.0D, 0.0D, 0.0D);
      }

   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
