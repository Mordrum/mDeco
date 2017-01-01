package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPTab;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class DMPBlockBrickSlab extends BlockSlab {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
   private String keyName;

   public DMPBlockBrickSlab(String name) {
      super(Material.GROUND);
      this.keyName = name;
      this.setUnlocalizedName(name + (this.isDouble()?"_slab_double":"_slab"));
      this.useNeighborBrightness = !this.isDouble();
      this.setHardness(1.2F);
      this.setResistance(5.0F);
      this.setSoundType(SoundType.STONE);
      this.setDefaultState(this.getDefaultState().withProperty(VARIANT, EnumType.normal).withProperty(HALF, this.isDouble()?EnumBlockHalf.TOP:EnumBlockHalf.BOTTOM));
      if(!this.isDouble()) {
         this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      }

   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, HALF, VARIANT);
   }

   public IBlockState getStateFromMeta(int meta) {
      IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));
      if(!this.isDouble()) {
         iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0?EnumBlockHalf.BOTTOM:EnumBlockHalf.TOP);
      }

      return iblockstate;
   }

   public int getMetaFromState(IBlockState state) {
      byte i = 0;
      int i1 = i | ((EnumType)state.getValue(VARIANT)).getMetadata();
      if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
         i1 |= 8;
      }

      return i1;
   }

   public MapColor getMapColor(IBlockState state) {
      return MapColor.STONE;
   }

   public int damageDropped(IBlockState state) {
      return ((EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public final Item getItemDropped(IBlockState blockState, Random random, int unused) {
      return this.getSlabItem();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(this.getSlabItem(), 1, 0);
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(this.getSlabItem(), 1, ((EnumType)state.getValue(VARIANT)).getMetadata());
   }

   private Item getSlabItem() {
      return Item.getItemFromBlock((Block) MDeco.blocks.ancientSlabs.get(this.keyName));
   }

   public String getUnlocalizedName(int meta) {
      return this.getUnlocalizedName();
   }

   public IProperty getVariantProperty() {
      return VARIANT;
   }

   public Comparable getTypeForItem(ItemStack stack) {
      return EnumType.byMetadata(stack.getMetadata() & 7);
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return this.isDouble()?super.shouldSideBeRendered(blockState, blockAccess, pos, side):(
		      !(side != EnumFacing.UP && side != EnumFacing.DOWN &&
                      !super.shouldSideBeRendered(blockState, blockAccess, pos, side)) &&
				      super.shouldSideBeRendered(blockState, blockAccess, pos, side));
   }

   @SideOnly(Side.CLIENT)
   protected static boolean isHalfSlab(IBlockState p_185675_0_) {
      Block block = p_185675_0_.getBlock();
      return block instanceof DMPBlockBrickSlab;
   }

   public enum EnumType implements IStringSerializable {
      normal(0),
      mossy(1);

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

      public static EnumType byMetadata(int meta) {
         if(meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
         }

         return META_LOOKUP[meta];
      }

      static {
         EnumType[] var0 = values();
         int var1 = var0.length;

         for (EnumType blockslab$enumtype : var0) {
            META_LOOKUP[blockslab$enumtype.getMetadata()] = blockslab$enumtype;
         }

      }
   }
}
