package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemFireplaceScreen;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockFireplaceScreen extends DMPBlockConnectSides {
   public static final PropertyBool BOTTOM = PropertyBool.create("bottom");
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.34375D, 1.0D, 1.0D, 0.53125D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.46875D, 1.0D, 1.0D, 0.65625D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.34375D, 0.0D, 0.0D, 0.53125D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.46875D, 0.0D, 0.0D, 0.65625D, 1.0D, 1.0D);

   public DMPBlockFireplaceScreen(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(BOTTOM, Boolean.valueOf(true)).withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, Boolean.valueOf(false)).withProperty(RIGHT, Boolean.valueOf(false)));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemFireplaceScreen.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{BOTTOM, FACING, LEFT, RIGHT});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      IBlockState newState = super.getActualState(state, worldIn, pos);
      Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
      if(blockAbove == this) {
         newState = newState.withProperty(BOTTOM, Boolean.valueOf(true));
      } else {
         newState = newState.withProperty(BOTTOM, Boolean.valueOf(false));
      }

      return newState;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer getBlockLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      return facing == EnumFacing.NORTH?AABB_N:(facing == EnumFacing.SOUTH?AABB_S:(facing == EnumFacing.WEST?AABB_W:(facing == EnumFacing.EAST?AABB_E:
		      FULL_BLOCK_AABB)));
   }
}
