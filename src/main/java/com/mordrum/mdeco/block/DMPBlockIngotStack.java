package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemIngotStack;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.IProperty;
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

public class DMPBlockIngotStack extends DMPBlockBaseDecoration {
   public static final PropertyInteger STACKS = PropertyInteger.create("stacks", 0, 5);
   protected static final AxisAlignedBB[] STACK_HEIGHT = new AxisAlignedBB[]{new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.625D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.6875D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.75D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.8125D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D)};

   public DMPBlockIngotStack(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(STACKS, Integer.valueOf(0)));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemIngotStack.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{STACKS});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      return ((Integer)state.getValue(STACKS)).intValue();
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(STACKS, Integer.valueOf(meta));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      int stacks = ((Integer)base_state.getValue(STACKS)).intValue();
      return side == EnumFacing.UP?stacks == 5:side == EnumFacing.DOWN;
   }

   public int damageDropped(IBlockState state) {
      return this.getMetaFromState(state);
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      int stacks = ((Integer)state.getValue(STACKS)).intValue();
      ++stacks;
      if(stacks > 5) {
         stacks = 0;
      }

      worldIn.setBlockState(pos, state.withProperty(STACKS, Integer.valueOf(stacks)));
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return STACK_HEIGHT[((Integer)state.getValue(STACKS)).intValue()];
   }
}
