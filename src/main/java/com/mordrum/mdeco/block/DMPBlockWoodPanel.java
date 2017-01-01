package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemWoodPanel;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockWoodPanel extends DMPBlockDirectional {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 2);
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

   public DMPBlockWoodPanel(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, Integer.valueOf(0)).withProperty(FACING, EnumFacing.NORTH));
      this.setSoundType(SoundType.WOOD);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWoodPanel.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECTED, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(CONNECTED, Integer.valueOf(this.getConnectedIndex(worldIn, pos, (EnumFacing)state.getValue(FACING))));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return (EnumFacing)base_state.getValue(FACING) == side;
   }

   public int getConnectedIndex(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
      IBlockState stateConnection = worldIn.getBlockState(pos.offset(facing.getOpposite()));
      if(stateConnection != null && stateConnection.getBlock() == this) {
         if(facing == EnumFacing.NORTH) {
            if(stateConnection.getValue(FACING) == EnumFacing.WEST) {
               return 1;
            }

            if(stateConnection.getValue(FACING) == EnumFacing.EAST) {
               return 2;
            }
         } else if(facing == EnumFacing.SOUTH) {
            if(stateConnection.getValue(FACING) == EnumFacing.EAST) {
               return 1;
            }

            if(stateConnection.getValue(FACING) == EnumFacing.WEST) {
               return 2;
            }
         } else if(facing == EnumFacing.WEST) {
            if(stateConnection.getValue(FACING) == EnumFacing.SOUTH) {
               return 1;
            }

            if(stateConnection.getValue(FACING) == EnumFacing.NORTH) {
               return 2;
            }
         } else if(facing == EnumFacing.EAST) {
            if(stateConnection.getValue(FACING) == EnumFacing.NORTH) {
               return 1;
            }

            if(stateConnection.getValue(FACING) == EnumFacing.SOUTH) {
               return 2;
            }
         }

         return 0;
      } else {
         return 0;
      }
   }

   protected boolean canConnectTo(World worldIn, BlockPos pos) {
      Block block = worldIn.getBlockState(pos).getBlock();
      return block == null?false:block == this;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         return AABB_N;
      case 2:
         return AABB_S;
      case 3:
         return AABB_W;
      case 4:
         return AABB_E;
      default:
         return FULL_BLOCK_AABB;
      }
   }
}
