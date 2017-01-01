package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemKitchenPot;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DMPBlockKitchenPot extends DMPBlockBaseDecoration {
   protected static final AxisAlignedBB LARGE_POT = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.4375D, 0.875D);
   protected static final AxisAlignedBB MEDIUM_POT = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
   protected static final AxisAlignedBB FRYING_PAN = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D);
   public static final PropertyDirection FACING;
   public static final PropertyEnum VARIANT;

   public DMPBlockKitchenPot(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.large_pot));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemKitchenPot.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING, VARIANT);
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal((meta & 12) >> 2)).withProperty(VARIANT, EnumType.byMetadata(meta & 3));
   }

   public int getMetaFromState(IBlockState state) {
      byte b0 = 0;
      int i = b0 | ((EnumType)state.getValue(VARIANT)).getMetadata();
      i |= state.getValue(FACING).getHorizontalIndex() << 2;
      return i;
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      EnumType type = (EnumType)state.getValue(VARIANT);
      worldIn.setBlockState(pos, state.withProperty(VARIANT, type.getNextVariant()), 2);
      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      EnumType type = (EnumType)state.getValue(VARIANT);
      return type == EnumType.large_pot?LARGE_POT:(type == EnumType.medium_pot?MEDIUM_POT:(type == EnumType.frying_pan?FRYING_PAN:super.getBoundingBox(state, source, pos)));
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
      VARIANT = PropertyEnum.create("variant", EnumType.class);
   }

   public enum EnumType implements IStringSerializable {
      large_pot(0),
      medium_pot(1),
      frying_pan(2);

      private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;

      EnumType(int meta) {
         this.meta = meta;
      }

      public int getMetadata() {
         return this.meta;
      }

      public String getName() {
         return this.name();
      }

      public String toString() {
         return this.getName();
      }

      public EnumType getNextVariant() {
         int meta = this.getMetadata() + 1;
         if(meta > META_LOOKUP.length - 1) {
            meta = 0;
         }

         return byMetadata(meta);
      }

      public static EnumType byMetadata(int meta) {
         if(meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      static {
         EnumType[] var0 = values();
         int var1 = var0.length;

	      for (EnumType decoration$enumtype : var0) {
		      META_LOOKUP[decoration$enumtype.getMetadata()] = decoration$enumtype;
	      }

      }
   }
}
