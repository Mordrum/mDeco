package com.mordrum.mdeco.block;

import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetWall;
import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemCabinetWall;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPBlockCabinetWall extends DMPBlockStorage {
   protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.6875D);
   protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
   protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

   public DMPBlockCabinetWall(DMPDecoration decoration) {
      super(decoration);
      this.setRegistryName(this.decoration.name());
      GameRegistry.register(this);
      GameRegistry.register(new DMPItemCabinetWall(this).setRegistryName(this.decoration.name()));
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityCabinetWall();
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityCabinetWall)?null:(this.isBlocked(worldIn, pos)?null:(DMPTileEntityCabinetWall)tileentity);
   }

   @Override
   public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityCabinetWall) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(!worldIn.isRemote) {
         ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if(ilockablecontainer != null) {
            playerIn.openGui(MDeco.instance, 12, worldIn, pos.getX(), pos.getY(), pos.getZ());
         }
      }

      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch(state.getValue(FACING).ordinal()) {
      case 1:
         return AABB_N;
      case 2:
         return AABB_S;
      case 3:
         return AABB_W;
      case 4:
         return AABB_E;
      default:
         return FULL_BLOCK_AABB;
      }
   }
}
