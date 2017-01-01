package com.mordrum.mdeco.block;

import com.mordrum.mdeco.item.DMPItemKitchenKettle;
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

public class DMPBlockKitchenKettle extends DMPBlockBaseDecoration {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.453125D, 0.75D);
   public static final PropertyDirection FACING;
   public static final PropertyEnum VARIANT;

   public DMPBlockKitchenKettle(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.lid_off));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemKitchenKettle.class, this.decoration.name());
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
      return AABB;
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
      VARIANT = PropertyEnum.create("variant", EnumType.class);
   }

   public enum EnumType implements IStringSerializable {
      lid_off(0),
      lid_on(1);

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
