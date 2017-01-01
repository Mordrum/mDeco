package com.mordrum.mdeco.block;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.tileentity.DMPTileEntityWoodBox;
import com.mordrum.mdeco.item.DMPItemWoodBox;
import com.mordrum.mdeco.object.DMPDecoration;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class DMPBlockWoodBox extends DMPBlockStorage implements ITileEntityProvider {
   public DMPBlockWoodBox(DMPDecoration decoration) {
      super(decoration);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      com.mordrum.mdeco.Util.registerBlockAndItem(this, DMPItemWoodBox.class, this.decoration.name());
      this.registerOreDictName(this.decoration.oreDictName);
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new DMPTileEntityWoodBox();
   }

   protected ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return !(tileentity instanceof DMPTileEntityWoodBox)?null:(DMPTileEntityWoodBox)tileentity;
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING);
   }

   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
      return state;
   }

   @Override public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
      super.onNeighborChange(world, pos, neighbor);
      TileEntity tileentity = world.getTileEntity(pos);
      if(tileentity instanceof DMPTileEntityWoodBox) {
         tileentity.updateContainingBlockInfo();
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
      if(!worldIn.isRemote) {
         ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if(ilockablecontainer != null) {
            playerIn.openGui(MDeco.instance, 10, worldIn, pos.getX(), pos.getY(), pos.getZ());
         }
      }

      return true;
   }

   public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public boolean isFullyOpaque(IBlockState state) {
      return true;
   }
}
