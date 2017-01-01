package com.mordrum.mdeco.block;

import com.google.common.collect.UnmodifiableIterator;
import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPBuildingBlock;
import com.mordrum.mdeco.object.DMPTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DMPBlockPillar extends Block {
   public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumAxis.class);
   protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.15625D, 0.0D, 0.15625D, 0.84375D, 1.0D, 0.84375D);
   private boolean smallPillar;

   public DMPBlockPillar(DMPBuildingBlock buildingBlock, boolean smallPillar) {
      super(buildingBlock.material, buildingBlock.material.getMaterialMapColor());
      this.smallPillar = smallPillar;
      this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.y));
      this.setTickRandomly(false);
      this.setUnlocalizedName(buildingBlock.name());
      this.setCreativeTab(MDeco.creativeTabs.getCreativeTab(DMPTab.blocks));
      this.setHardness(1.5F);
      this.setResistance(10.0F);
      this.setSoundType(SoundType.STONE);
      this.setRegistryName(buildingBlock.name());
      GameRegistry.register(this);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{AXIS});
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumAxis axis = EnumAxis.getAxisFromMeta(meta);
      return this.getDefaultState().withProperty(AXIS, axis);
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumAxis)state.getValue(AXIS)).getMeta();
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
      return 0;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return this.smallPillar?AABB_SMALL:Block.FULL_BLOCK_AABB;
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
         switch (axis) {
            case X:
               return x;
            case Y:
               return y;
            case Z:
               return z;
            default:
               return y;
         }

      }
   }
}
