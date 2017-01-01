package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemFeedingTrough;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockFeedingTrough extends DMPBlockBaseDecoration {
   public static final PropertyBool CONNECT_FORE = PropertyBool.create("connectfore");
   public static final PropertyBool CONNECT_LEFT = PropertyBool.create("connectleft");
   public static final PropertyBool CONNECT_RIGHT = PropertyBool.create("connectright");
   public static final PropertyDirection FACING;
   protected static final AxisAlignedBB AABB_BASE_FOOD;
   protected static final AxisAlignedBB AABB_BASE_WATER;
   protected static final AxisAlignedBB AABB_N;
   protected static final AxisAlignedBB AABB_S;
   protected static final AxisAlignedBB AABB_W;
   protected static final AxisAlignedBB AABB_E;

   public DMPBlockFeedingTrough(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECT_FORE, Boolean.valueOf(false)).withProperty(CONNECT_LEFT, Boolean.valueOf(false)).withProperty(CONNECT_RIGHT, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemFeedingTrough.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECT_FORE, CONNECT_LEFT, CONNECT_RIGHT, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      boolean bFore = this.canConnectFore(state, worldIn, pos);
      boolean bLeft = this.canConnectLeft(state, worldIn, pos);
      boolean bRight = this.canConnectRight(state, worldIn, pos);
      if(bLeft && bRight) {
         bFore = false;
      }

      return this.getDefaultState().withProperty(FACING, (EnumFacing)state.getValue(FACING)).withProperty(CONNECT_FORE, Boolean.valueOf(bFore)).withProperty(CONNECT_LEFT, Boolean.valueOf(bLeft)).withProperty(CONNECT_RIGHT, Boolean.valueOf(bRight));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      if(!worldIn.isRemote && this.decoration.decorationType == DMPDecorationType.waterTrough) {
         float pixel = 0.0625F;
         float f = (float)pos.getY() + 1.0F - pixel * 3.0F;
         if(entityIn.isBurning() && entityIn.getEntityBoundingBox().minY <= (double)f) {
            entityIn.extinguish();
         }
      }

   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      float pixel = 0.0625F;
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      boolean fore = ((Boolean)state.getValue(CONNECT_FORE)).booleanValue();
      boolean left = ((Boolean)state.getValue(CONNECT_LEFT)).booleanValue();
      boolean right = ((Boolean)state.getValue(CONNECT_RIGHT)).booleanValue();
      float startX = 0.0F + 1.0F * pixel;
      float endX = 1.0F - 1.0F * pixel;
      float startZ = 0.0F + 1.0F * pixel;
      float endZ = 1.0F - 1.0F * pixel;
      if(facing == EnumFacing.NORTH) {
         if(fore) {
            endZ = 1.0F;
         }

         if(left) {
            startX = 0.0F;
         }

         if(right) {
            endX = 1.0F;
         }
      } else if(facing == EnumFacing.SOUTH) {
         if(fore) {
            startZ = 0.0F;
         }

         if(left) {
            endX = 1.0F;
         }

         if(right) {
            startX = 0.0F;
         }
      } else if(facing == EnumFacing.WEST) {
         if(fore) {
            endX = 1.0F;
         }

         if(left) {
            endZ = 1.0F;
         }

         if(right) {
            startZ = 0.0F;
         }
      } else if(facing == EnumFacing.EAST) {
         if(fore) {
            startX = 0.0F;
         }

         if(left) {
            startZ = 0.0F;
         }

         if(right) {
            endZ = 1.0F;
         }
      }

      return new AxisAlignedBB((double)startX, 0.0D, (double)startZ, (double)endX, (double)(1.0F - pixel * 3.0F), (double)endZ);
   }

   private boolean canConnectFore(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = worldIn.getBlockState(pos.offset(facing.getOpposite()));
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         if(facing != adjacentFacing && facing != adjacentFacing.getOpposite()) {
            IBlockState checkState1 = worldIn.getBlockState(pos.offset(adjacentFacing));
            IBlockState checkState2 = worldIn.getBlockState(pos.offset(adjacentFacing.getOpposite()));
            return checkState1.getBlock() == this && (EnumFacing)checkState1.getValue(FACING) == facing?true:checkState2.getBlock() == this && (EnumFacing)checkState2.getValue(FACING) == facing;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean canConnectLeft(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = null;
      EnumFacing facingOffset = EnumFacing.NORTH;
      if(facing == EnumFacing.NORTH) {
         facingOffset = EnumFacing.WEST;
      } else if(facing == EnumFacing.SOUTH) {
         facingOffset = EnumFacing.EAST;
      } else if(facing == EnumFacing.WEST) {
         facingOffset = EnumFacing.SOUTH;
      } else if(facing == EnumFacing.EAST) {
         facingOffset = EnumFacing.NORTH;
      }

      BlockPos posConnect = pos.offset(facingOffset);
      adjacentState = worldIn.getBlockState(posConnect);
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         return adjacentFacing == facing?true:(adjacentFacing != facingOffset?false:this.canConnectFore(worldIn.getBlockState(posConnect), worldIn, posConnect));
      } else {
         return false;
      }
   }

   private boolean canConnectRight(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = null;
      EnumFacing facingOffset = EnumFacing.NORTH;
      if(facing == EnumFacing.NORTH) {
         facingOffset = EnumFacing.EAST;
      } else if(facing == EnumFacing.SOUTH) {
         facingOffset = EnumFacing.WEST;
      } else if(facing == EnumFacing.WEST) {
         facingOffset = EnumFacing.NORTH;
      } else if(facing == EnumFacing.EAST) {
         facingOffset = EnumFacing.SOUTH;
      }

      BlockPos posConnect = pos.offset(facingOffset);
      adjacentState = worldIn.getBlockState(posConnect);
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         return adjacentFacing == facing?true:(adjacentFacing != facingOffset?false:this.canConnectFore(worldIn.getBlockState(posConnect), worldIn, posConnect));
      } else {
         return false;
      }
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
      AABB_BASE_FOOD = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.2875D, 1.0D);
      AABB_BASE_WATER = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.15D, 1.0D);
      AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 0.125D);
      AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 0.8125D, 1.0D);
      AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 0.8125D, 1.0D);
      AABB_E = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
   }
}
