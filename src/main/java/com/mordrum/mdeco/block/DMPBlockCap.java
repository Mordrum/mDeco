package com.mordrum.mdeco.block;

import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.item.DMPItemCap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPBlockCap extends DMPBlockConnectOne {
   public DMPBlockCap(DMPDecoration decoration) {
      super(decoration);
      this.setRegistryName(this.decoration.name());
      GameRegistry.register(this);
      GameRegistry.register(new DMPItemCap(this).setRegistryName(this.decoration.name()));
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      IBlockState stateTarget = worldIn.getBlockState(pos.offset(side.getOpposite()));
      Block block = stateTarget.getBlock();
      return block == null?false:(side == EnumFacing.DOWN && block instanceof DMPBlockWallLantern?true:(side != EnumFacing.DOWN && side != EnumFacing.UP || block != Blocks.COBBLESTONE_WALL && !(block instanceof BlockWall)?block.isSideSolid(stateTarget, worldIn, pos.offset(side.getOpposite()), side):true));
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      int direction = ((Integer)base_state.getValue(CONNECTED)).intValue();
      switch(direction) {
      case 0:
         return side == EnumFacing.UP;
      case 1:
         return side == EnumFacing.DOWN;
      case 2:
         return side == EnumFacing.SOUTH;
      case 3:
         return side == EnumFacing.NORTH;
      case 4:
         return side == EnumFacing.EAST;
      case 5:
         return side == EnumFacing.WEST;
      default:
         return false;
      }
   }

   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
      Block block = state.getBlock();
      if(block != null && block == this) {
         if(!this.canBlockStay(worldIn, pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

      }
   }

   private boolean canBlockStay(World worldIn, BlockPos pos) {
      EnumFacing facing = EnumFacing.getFront(((Integer)worldIn.getBlockState(pos).getValue(CONNECTED)).intValue());
      return this.canPlaceBlockOnSide(worldIn, pos, facing);
   }
}
