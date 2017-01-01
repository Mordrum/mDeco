package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemWoodCrate;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityWoodCrate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class DMPBlockWoodCrate extends DMPBlockStorage implements ITileEntityProvider {
   public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 8);

   public DMPBlockWoodCrate(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, Integer.valueOf(0)).withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWoodCrate.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityWoodCrate();
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityWoodCrate)?null:(DMPTileEntityWoodCrate)tileentity;
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECTED, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state.withProperty(CONNECTED, Integer.valueOf(this.getConnectedState(worldIn, pos, state)));
   }

   @Override
   public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityWoodCrate) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(!worldIn.isRemote) {
         ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if(ilockablecontainer != null) {
            playerIn.openGui(MDeco.instance, 16, worldIn, pos.getX(), pos.getY(), pos.getZ());
         }
      }

      return true;
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP || side == EnumFacing.DOWN;
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullyOpaque(IBlockState state) {
      return true;
   }

   private int getConnectedState(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
      Block block1;
      Block block2;
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         block1 = worldIn.getBlockState(pos.west()).getBlock();
         block2 = worldIn.getBlockState(pos.east()).getBlock();
         break;
      case 2:
         block1 = worldIn.getBlockState(pos.east()).getBlock();
         block2 = worldIn.getBlockState(pos.west()).getBlock();
         break;
      case 3:
         block1 = worldIn.getBlockState(pos.south()).getBlock();
         block2 = worldIn.getBlockState(pos.north()).getBlock();
         break;
      case 4:
         block1 = worldIn.getBlockState(pos.north()).getBlock();
         block2 = worldIn.getBlockState(pos.south()).getBlock();
         break;
      default:
         return 0;
      }

      boolean connected = false;
      byte connected1;
      if(block1 == Blocks.AIR) {
         if(block2 == Blocks.AIR) {
            connected1 = 0;
         } else if(block2 instanceof DMPBlockWoodCrate) {
            connected1 = 2;
         } else {
            connected1 = 5;
         }
      } else if(block1 instanceof DMPBlockWoodCrate) {
         if(block2 == Blocks.AIR) {
            connected1 = 1;
         } else if(block2 instanceof DMPBlockWoodCrate) {
            connected1 = 3;
         } else {
            connected1 = 8;
         }
      } else if(block2 == Blocks.AIR) {
         connected1 = 4;
      } else if(block2 instanceof DMPBlockWoodCrate) {
         connected1 = 7;
      } else {
         connected1 = 6;
      }

      return connected1;
   }
}
