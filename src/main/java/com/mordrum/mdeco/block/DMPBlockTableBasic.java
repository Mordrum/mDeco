package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemTableBasic;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockTableBasic extends DMPBlockConnectableDirectional {
   public DMPBlockTableBasic(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemTableBasic.class, decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP;
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullyOpaque(IBlockState state) {
      return true;
   }

   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      int playerOrientation = placer.getHorizontalFacing().getHorizontalIndex() != 0 && placer.getHorizontalFacing().getHorizontalIndex() != 2?0:1;
      int orientation = this.getOrientationAfterPlaced(worldIn, pos, playerOrientation);
      if(orientation < 0) {
         orientation = playerOrientation;
      }

      return this.getDefaultState().withProperty(ORIENTATION, Integer.valueOf(orientation));
   }

   private int getOrientationAfterPlaced(World worldIn, BlockPos pos, int playerOrientation) {
      Block block = worldIn.getBlockState(pos.north()).getBlock();
      int neighborOrientation;
      if(block == this) {
         neighborOrientation = Integer.valueOf(((Integer)worldIn.getBlockState(pos.north()).getValue(ORIENTATION)).intValue()).intValue();
         neighborOrientation = neighborOrientation != 0 && neighborOrientation != 2?0:1;
         if(playerOrientation == neighborOrientation) {
            return neighborOrientation;
         }
      }

      block = worldIn.getBlockState(pos.east()).getBlock();
      if(block == this) {
         neighborOrientation = Integer.valueOf(((Integer)worldIn.getBlockState(pos.east()).getValue(ORIENTATION)).intValue()).intValue();
         neighborOrientation = neighborOrientation != 0 && neighborOrientation != 2?0:1;
         if(playerOrientation == neighborOrientation) {
            return neighborOrientation;
         }
      }

      block = worldIn.getBlockState(pos.south()).getBlock();
      if(block == this) {
         neighborOrientation = Integer.valueOf(((Integer)worldIn.getBlockState(pos.south()).getValue(ORIENTATION)).intValue()).intValue();
         neighborOrientation = neighborOrientation != 0 && neighborOrientation != 2?0:1;
         if(playerOrientation == neighborOrientation) {
            return neighborOrientation;
         }
      }

      block = worldIn.getBlockState(pos.west()).getBlock();
      if(block == this) {
         neighborOrientation = Integer.valueOf(((Integer)worldIn.getBlockState(pos.west()).getValue(ORIENTATION)).intValue()).intValue();
         neighborOrientation = neighborOrientation != 0 && neighborOrientation != 2?0:1;
         if(playerOrientation == neighborOrientation) {
            return neighborOrientation;
         }
      }

      return playerOrientation;
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
      Block block = worldIn.getBlockState(pos).getBlock();
      return block == this || block instanceof BlockWorkbench || block instanceof DMPBlockWorkbenchFoundry || block instanceof DMPBlockWorkbenchSaw;
   }
}
