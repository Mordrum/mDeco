package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockDeskHutchWoodBasic;
import com.mordrum.mdeco.block.DMPBlockDeskWoodBasic;
import com.mordrum.mdeco.core.DMPHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DMPItemDeskHutchWoodBasic extends ItemBlock {
   private DMPBlockDeskHutchWoodBasic decorationBlock;

   public DMPItemDeskHutchWoodBasic(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockDeskHutchWoodBasic)block;
   }

   public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      BlockPos posTarget = null;
      if(facing == EnumFacing.UP) {
         posTarget = pos.up();
      } else if(facing == EnumFacing.DOWN) {
         posTarget = pos.down();
      } else if(facing == EnumFacing.NORTH) {
         posTarget = pos.north();
      } else if(facing == EnumFacing.SOUTH) {
         posTarget = pos.south();
      } else if(facing == EnumFacing.WEST) {
         posTarget = pos.west();
      } else if(facing == EnumFacing.EAST) {
         posTarget = pos.east();
      }

      if(posTarget != null && worldIn.isAirBlock(posTarget)) {
         if(!(worldIn.getBlockState(posTarget.down()).getBlock() instanceof DMPBlockDeskWoodBasic)) {
            return EnumActionResult.FAIL;
         } else {
            EnumFacing enumFacing = (EnumFacing)worldIn.getBlockState(posTarget.down()).getValue(DMPBlockDeskWoodBasic.FACING);
            if(playerIn.canPlayerEdit(posTarget, facing, stack)) {
               IBlockState iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockDeskHutchWoodBasic.FACING, enumFacing);
               worldIn.setBlockState(posTarget, iblockstate1, 3);
               DMPHelper.playObjectSound(worldIn, pos, playerIn, this.block);
               stack.setCount(stack.getCount() - 1);
               return EnumActionResult.SUCCESS;
            } else {
               return EnumActionResult.FAIL;
            }
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
