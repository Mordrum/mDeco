package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockConnectAll extends DMPBlockBaseDecoration {
   public static final PropertyBool DOWN = PropertyBool.create("down");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool UP = PropertyBool.create("up");
   public static final PropertyBool WEST = PropertyBool.create("west");
   protected int collisionPixelSize = 4;
   protected int hitboxPixelSize = 4;

   public DMPBlockConnectAll(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(DOWN, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(NORTH, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(UP, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, DOWN, EAST, NORTH, SOUTH, UP, WEST);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.getBlock() != this?state:state.withProperty(DOWN, this.canConnectTo(worldIn, pos, EnumFacing.DOWN)).withProperty(EAST, this
              .canConnectTo(worldIn, pos, EnumFacing.EAST)).withProperty(NORTH, this
              .canConnectTo(worldIn, pos, EnumFacing.NORTH)).withProperty(SOUTH, this
              .canConnectTo(worldIn, pos, EnumFacing.SOUTH)).withProperty(UP, this
              .canConnectTo(worldIn, pos, EnumFacing.UP)).withProperty(WEST, this
              .canConnectTo(worldIn, pos, EnumFacing.WEST));
   }

   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return worldIn.isSideSolid(pos.offset(side.getOpposite()), side, false);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      if(state.getBlock() != this) {
         return FULL_BLOCK_AABB;
      } else {
         boolean connectDown = state.getValue(DOWN);
         boolean connectEast = state.getValue(EAST);
         boolean connectNorth = state.getValue(NORTH);
         boolean connectSouth = state.getValue(SOUTH);
         boolean connectUp = state.getValue(UP);
         boolean connectWest = state.getValue(WEST);
         float pixel = 0.0625F;
         float x1 = 0.5F - pixel * (float)this.hitboxPixelSize;
         float x2 = 0.5F + pixel * (float)this.hitboxPixelSize;
         float y1 = 0.5F - pixel * (float)this.hitboxPixelSize;
         float y2 = 0.5F + pixel * (float)this.hitboxPixelSize;
         float z1 = 0.5F - pixel * (float)this.hitboxPixelSize;
         float z2 = 0.5F + pixel * (float)this.hitboxPixelSize;
         if(connectNorth) {
            z1 = 0.0F;
         }

         if(connectSouth) {
            z2 = 1.0F;
         }

         if(connectWest) {
            x1 = 0.0F;
         }

         if(connectEast) {
            x2 = 1.0F;
         }

         if(connectDown) {
            y1 = 0.0F;
         }

         if(connectUp) {
            y2 = 1.0F;
         }

         return new AxisAlignedBB((double)x1, (double)y1, (double)z1, (double)x2, (double)y2, (double)z2);
      }
   }

   protected boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side));
      return worldIn.getBlockState(pos.offset(side)).getBlock().isSideSolid(stateTarget, worldIn, pos.offset(side), side.getOpposite());
   }
}
