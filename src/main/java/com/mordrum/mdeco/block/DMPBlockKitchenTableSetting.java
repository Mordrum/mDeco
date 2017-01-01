package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.item.DMPItemKitchenTableSetting;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.tileentity.DMPTileEntityKitchenTableSetting;
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

public class DMPBlockKitchenTableSetting extends DMPBlockStorage {
   protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.325D, 0.875D);

   public DMPBlockKitchenTableSetting(DMPDecoration decoration) {
      super(decoration);
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemKitchenTableSetting.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityKitchenTableSetting();
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityKitchenTableSetting)?null:(this.isBlocked(worldIn, pos)?null:(DMPTileEntityKitchenTableSetting)tileentity);
   }

   @Override
   public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if (tileentity instanceof DMPTileEntityKitchenTableSetting) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(!worldIn.isRemote) {
         ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if(ilockablecontainer != null) {
            playerIn.openGui(MDeco.instance, 18, worldIn, pos.getX(), pos.getY(), pos.getZ());
         }
      }

      return true;
   }

   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return AABB;
   }
}
