package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemPalletStack;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockPalletStack extends DMPBlockBaseDecoration {
   public static final PropertyInteger STACKS = PropertyInteger.create("stacks", 0, 4);
   protected static final AxisAlignedBB[] STACK_HEIGHT = new AxisAlignedBB[]{new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.203125D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.40625D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.609375D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.8125D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D)};

   public DMPBlockPalletStack(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(STACKS, 0));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemPalletStack.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, STACKS);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      return state.getValue(STACKS);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(STACKS, meta);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      int stacks = base_state.getValue(STACKS);
      return side == EnumFacing.UP?stacks == 4:side == EnumFacing.DOWN;
   }

   public int damageDropped(IBlockState state) {
      return this.getMetaFromState(state);
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      int stacks = state.getValue(STACKS);
      ++stacks;
      if(stacks > 4) {
         stacks = 0;
      }

      worldIn.setBlockState(pos, state.withProperty(STACKS, stacks));
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return STACK_HEIGHT[state.getValue(STACKS)];
   }
}
