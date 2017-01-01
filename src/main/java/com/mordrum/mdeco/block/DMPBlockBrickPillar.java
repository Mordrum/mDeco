package com.mordrum.mdeco.block;

import com.google.common.collect.UnmodifiableIterator;
import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemBrickPillar;
import com.mordrum.mdeco.object.DMPTab;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class DMPBlockBrickPillar extends Block {
   public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumAxis.class);
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", DMPBlockBrick.EnumType.class);
   protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.15625D, 0.0D, 0.15625D, 0.84375D, 1.0D, 0.84375D);
   private boolean smallPillar;

   public DMPBlockBrickPillar(String unlocalizedName, boolean smallPillar) {
      super(Material.GROUND, MapColor.STONE);
      this.smallPillar = smallPillar;
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.y).withProperty(VARIANT, DMPBlockBrick.EnumType.normal));
      this.setTickRandomly(false);
      this.setUnlocalizedName(unlocalizedName);
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      this.setHardness(1.5F);
      this.setResistance(10.0F);
      this.setSoundType(SoundType.STONE);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemBrickPillar.class, unlocalizedName);
      OreDictionary.registerOre(unlocalizedName, this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{AXIS, VARIANT});
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumAxis axis = EnumAxis.getAxisFromMeta((meta & 12) >> 2);
      return this.getDefaultState().withProperty(AXIS, axis).withProperty(VARIANT, DMPBlockBrick.EnumType.byMetadata(meta & 3));
   }

   public int getMetaFromState(IBlockState state) {
      byte b0 = 0;
      int i = b0 | ((DMPBlockBrick.EnumType)state.getValue(VARIANT)).getMetadata();
      i |= ((EnumAxis)state.getValue(AXIS)).getMeta() << 2;
      return i;
   }

   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean isFullCube(IBlockState state) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
      IBlockState state = world.getBlockState(pos);
      UnmodifiableIterator var5 = state.getProperties().keySet().iterator();

      IProperty prop;
      do {
         if(!var5.hasNext()) {
            return false;
         }

         prop = (IProperty)var5.next();
      } while(!prop.getName().equals("axis"));

      world.setBlockState(pos, state.cycleProperty(prop));
      return true;
   }

   public int damageDropped(IBlockState state) {
      return ((DMPBlockBrick.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
      DMPBlockBrick.EnumType[] var4 = DMPBlockBrick.EnumType.values();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         DMPBlockBrick.EnumType blockwall$enumtype = var4[var6];
         list.add(new ItemStack(itemIn, 1, blockwall$enumtype.getMetadata()));
      }

   }

   protected ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, ((DMPBlockBrick.EnumType)state.getValue(VARIANT)).getMetadata());
   }

   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      return this.smallPillar?AABB_SMALL:Block.FULL_BLOCK_AABB;
   }

   @SideOnly(Side.CLIENT)
   public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      return this.getCollisionBoundingBox(state, worldIn, pos).offset(pos);
   }

   public static enum EnumAxis implements IStringSerializable {
      x(0),
      y(1),
      z(2);

      private final int meta;

      private EnumAxis(int meta) {
         this.meta = meta;
      }

      public int getMeta() {
         return this.meta;
      }

      public String getName() {
         return this.name();
      }

      public String toString() {
         return this.getName();
      }

      public static EnumAxis getAxisFromMeta(int meta) {
         switch(meta) {
         case 0:
            return x;
         case 1:
            return y;
         case 2:
            return z;
         default:
            return y;
         }
      }

      public static EnumAxis fromFacingAxis(Axis axis) {
         switch(axis.ordinal()) {
         case 1:
            return x;
         case 2:
            return y;
         case 3:
            return z;
         default:
            return y;
         }
      }
   }
}
