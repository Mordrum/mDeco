package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemSofa;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntitySofa;
import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class DMPBlockSofa extends DMPBlockStorage implements ITileEntityProvider {
   public static final PropertyBool CONNECT_FORE = PropertyBool.create("connectfore");
   public static final PropertyBool CONNECT_LEFT = PropertyBool.create("connectleft");
   public static final PropertyBool CONNECT_RIGHT = PropertyBool.create("connectright");
   public static final PropertyDirection FACING;

   public DMPBlockSofa(DMPDecoration decoration) {
      super(decoration);
      this.setTickRandomly(false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONNECT_FORE, Boolean.valueOf(false)).withProperty(CONNECT_LEFT, Boolean.valueOf(false)).withProperty(CONNECT_RIGHT, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemSofa.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{CONNECT_FORE, CONNECT_LEFT, CONNECT_RIGHT, FACING});
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      boolean bFore = this.canConnectFore(state, worldIn, pos);
      boolean bLeft = this.canConnectLeft(state, worldIn, pos);
      boolean bRight = this.canConnectRight(state, worldIn, pos);
      if(bLeft && bRight) {
         bFore = false;
      }

      return this.getDefaultState().withProperty(FACING, (EnumFacing)state.getValue(FACING)).withProperty(CONNECT_FORE, Boolean.valueOf(bFore)).withProperty(CONNECT_LEFT, Boolean.valueOf(bLeft)).withProperty(CONNECT_RIGHT, Boolean.valueOf(bRight));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
   }

   public IBlockState getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntitySofa)?null:(this.isBlocked(worldIn, pos)?null:(DMPTileEntitySofa)tileentity);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntitySofa();
   }

   @Override public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntitySofa) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(worldIn.isRemote) {
         return true;
      } else {
         playerIn.openGui(MDeco.instance, 25, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return false;
   }

   private boolean canConnectFore(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = worldIn.getBlockState(pos.offset(facing.getOpposite()));
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         if(facing != adjacentFacing && facing != adjacentFacing.getOpposite()) {
            IBlockState checkState1 = worldIn.getBlockState(pos.offset(adjacentFacing));
            IBlockState checkState2 = worldIn.getBlockState(pos.offset(adjacentFacing.getOpposite()));
            return checkState1.getBlock() == this && (EnumFacing)checkState1.getValue(FACING) == facing?true:checkState2.getBlock() == this && (EnumFacing)checkState2.getValue(FACING) == facing;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean canConnectLeft(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = null;
      EnumFacing facingOffset = EnumFacing.NORTH;
      if(facing == EnumFacing.NORTH) {
         facingOffset = EnumFacing.WEST;
      } else if(facing == EnumFacing.SOUTH) {
         facingOffset = EnumFacing.EAST;
      } else if(facing == EnumFacing.WEST) {
         facingOffset = EnumFacing.SOUTH;
      } else if(facing == EnumFacing.EAST) {
         facingOffset = EnumFacing.NORTH;
      }

      BlockPos posConnect = pos.offset(facingOffset);
      adjacentState = worldIn.getBlockState(posConnect);
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         return adjacentFacing == facing?true:(adjacentFacing != facingOffset?false:this.canConnectFore(worldIn.getBlockState(posConnect), worldIn, posConnect));
      } else {
         return false;
      }
   }

   private boolean canConnectRight(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);
      IBlockState adjacentState = null;
      EnumFacing facingOffset = EnumFacing.NORTH;
      if(facing == EnumFacing.NORTH) {
         facingOffset = EnumFacing.EAST;
      } else if(facing == EnumFacing.SOUTH) {
         facingOffset = EnumFacing.WEST;
      } else if(facing == EnumFacing.WEST) {
         facingOffset = EnumFacing.NORTH;
      } else if(facing == EnumFacing.EAST) {
         facingOffset = EnumFacing.SOUTH;
      }

      BlockPos posConnect = pos.offset(facingOffset);
      adjacentState = worldIn.getBlockState(posConnect);
      if(adjacentState != null && adjacentState.getBlock() == this) {
         EnumFacing adjacentFacing = (EnumFacing)adjacentState.getValue(FACING);
         return adjacentFacing == facing?true:(adjacentFacing != facingOffset?false:this.canConnectFore(worldIn.getBlockState(posConnect), worldIn, posConnect));
      } else {
         return false;
      }
   }

   public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos posIn, AxisAlignedBB mask, List list, Entity collidingEntity) {
      TileEntity te = worldIn.getTileEntity(posIn);
      if(te instanceof DMPTileEntitySofa) {
         DMPTileEntitySofa tileEntity = (DMPTileEntitySofa)te;
         float pixel = 0.0625F;
         float adjustBack = tileEntity.hasBackCushion()?pixel * 2.0F:0.0F;
         float adjustSeat = tileEntity.hasSeatCushion()?pixel * 2.0F:0.0F;
         AxisAlignedBB aabb = new AxisAlignedBB((double)(0.0F + pixel), 0.0D, (double)(0.0F + pixel), (double)(1.0F - pixel), (double)(pixel * 6.0F + adjustSeat), (double)(1.0F - pixel));
         addCollisionBoxToList(posIn, mask, list, aabb);
         IBlockState actualState = this.getActualState(worldIn.getBlockState(posIn), worldIn, posIn);
         EnumFacing facing = (EnumFacing)actualState.getValue(FACING);
         boolean fore = ((Boolean)actualState.getValue(CONNECT_FORE)).booleanValue();
         boolean left = ((Boolean)actualState.getValue(CONNECT_LEFT)).booleanValue();
         boolean right = ((Boolean)actualState.getValue(CONNECT_RIGHT)).booleanValue();
         if(facing == EnumFacing.NORTH) {
            aabb = new AxisAlignedBB(0.0D, 0.0D, (double)pixel, 1.0D, 1.0D, (double)(pixel * 2.0F + adjustBack));
            addCollisionBoxToList(posIn, mask, list, aabb);
            if(fore) {
               if(left) {
                  aabb = new AxisAlignedBB((double)(pixel * 14.0F - adjustBack), 0.0D, (double)(pixel * 2.0F), (double)(pixel * 15.0F), 1.0D, 1.0D);
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(right) {
                  aabb = new AxisAlignedBB((double)pixel, 0.0D, (double)(pixel * 2.0F), (double)(pixel * 2.0F + adjustBack), 1.0D, 1.0D);
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            } else {
               if(!left) {
                  aabb = new AxisAlignedBB(0.0D, 0.0D, (double)(pixel * 2.0F), (double)(pixel * 2.0F), (double)(pixel * 12.0F), (double)(pixel * 15.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(!right) {
                  aabb = new AxisAlignedBB((double)(pixel * 14.0F), 0.0D, (double)(pixel * 2.0F), 1.0D, (double)(pixel * 12.0F), (double)(pixel * 15.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            }
         } else if(facing == EnumFacing.SOUTH) {
            aabb = new AxisAlignedBB(0.0D, 0.0D, (double)(pixel * 14.0F - adjustBack), 1.0D, 1.0D, (double)(pixel * 15.0F));
            addCollisionBoxToList(posIn, mask, list, aabb);
            if(fore) {
               if(left) {
                  aabb = new AxisAlignedBB((double)pixel, 0.0D, (double)pixel, (double)(pixel * 2.0F + adjustBack), 1.0D, (double)(pixel * 14.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(right) {
                  aabb = new AxisAlignedBB((double)(pixel * 14.0F - adjustBack), 0.0D, (double)pixel, (double)(pixel * 15.0F), 1.0D, (double)(pixel * 14.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            } else {
               if(!left) {
                  aabb = new AxisAlignedBB((double)(pixel * 14.0F), 0.0D, (double)pixel, 1.0D, (double)(pixel * 12.0F), (double)(pixel * 14.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(!right) {
                  aabb = new AxisAlignedBB(0.0D, 0.0D, (double)pixel, (double)(pixel * 2.0F), (double)(pixel * 12.0F), (double)(pixel * 14.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            }
         } else if(facing == EnumFacing.WEST) {
            aabb = new AxisAlignedBB((double)pixel, 0.0D, 0.0D, (double)(pixel * 2.0F + adjustBack), 1.0D, 1.0D);
            addCollisionBoxToList(posIn, mask, list, aabb);
            if(fore) {
               if(left) {
                  aabb = new AxisAlignedBB((double)(pixel * 2.0F), 0.0D, (double)pixel, 1.0D, 1.0D, (double)(pixel * 2.0F + adjustBack));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(right) {
                  aabb = new AxisAlignedBB((double)(pixel * 2.0F), 0.0D, (double)(pixel * 14.0F - adjustBack), 1.0D, 1.0D, (double)(pixel * 15.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            } else {
               if(!left) {
                  aabb = new AxisAlignedBB((double)(pixel * 2.0F), 0.0D, (double)(pixel * 14.0F), (double)(pixel * 15.0F), (double)(pixel * 12.0F), 1.0D);
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(!right) {
                  aabb = new AxisAlignedBB((double)(pixel * 2.0F), 0.0D, 0.0D, (double)(pixel * 15.0F), (double)(pixel * 12.0F), (double)(pixel * 2.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            }
         } else if(facing == EnumFacing.EAST) {
            aabb = new AxisAlignedBB((double)(pixel * 14.0F - adjustBack), 0.0D, 0.0D, (double)(pixel * 15.0F), 1.0D, 1.0D);
            addCollisionBoxToList(posIn, mask, list, aabb);
            if(fore) {
               if(left) {
                  aabb = new AxisAlignedBB(0.0D, 0.0D, (double)(pixel * 14.0F - adjustBack), (double)(pixel * 14.0F), 1.0D, (double)(pixel * 15.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(right) {
                  aabb = new AxisAlignedBB(0.0D, 0.0D, (double)pixel, (double)(pixel * 14.0F), 1.0D, (double)(pixel * 2.0F + adjustBack));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            } else {
               if(!left) {
                  aabb = new AxisAlignedBB((double)pixel, 0.0D, 0.0D, (double)(pixel * 14.0F), (double)(pixel * 12.0F), (double)(pixel * 2.0F));
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }

               if(!right) {
                  aabb = new AxisAlignedBB((double)pixel, 0.0D, (double)(pixel * 14.0F), (double)(pixel * 14.0F), (double)(pixel * 12.0F), 1.0D);
                  addCollisionBoxToList(posIn, mask, list, aabb);
               }
            }
         }

      }
   }

   static {
      FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);
   }
}
