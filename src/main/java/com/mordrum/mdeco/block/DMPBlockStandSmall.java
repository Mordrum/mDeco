package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemStandSmall;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DMPBlockStandSmall extends DMPBlockBaseDecoration {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 3);
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

   public DMPBlockStandSmall(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemStandSmall.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECTED});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
      Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();
      return blockAbove != null && blockBelow != null?(!(blockAbove instanceof DMPBlockStandSmall) && !(blockAbove instanceof DMPBlockLantern)?(!(blockBelow instanceof DMPBlockStandSmall) && !(blockBelow instanceof DMPBlockLantern)?this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(3)):this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(2))):(!(blockBelow instanceof DMPBlockStandSmall) && !(blockBelow instanceof DMPBlockLantern)?this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(1)):this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(0)))):worldIn.getBlockState(pos);
   }

   public int getMetaFromState(IBlockState state) {
      return ((Integer)state.getValue(CONNECTED)).intValue();
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(CONNECTED, Integer.valueOf(meta));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP;
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullyOpaque(IBlockState state) {
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
