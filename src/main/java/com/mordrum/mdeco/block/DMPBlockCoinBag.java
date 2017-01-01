package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemCoinBag;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockCoinBag extends DMPBlockDirectional {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.3125D, 0.875D);

   public DMPBlockCoinBag(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemCoinBag.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      this.rotateBlock(worldIn, pos, state);
      return true;
   }

   private void rotateBlock(World worldIn, BlockPos pos, IBlockState state) {
      EnumFacing facing = state.getValue(FACING);
      facing = facing.rotateY();
      worldIn.setBlockState(pos, state.withProperty(FACING, facing), 3);
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
