package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockPole;
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

public class DMPItemPole extends ItemBlock {
   private DMPBlockPole decorationBlock;

   public DMPItemPole(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockPole)block;
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
         IBlockState iblockstate1 = this.decorationBlock.getDefaultState();
         if(!playerIn.canPlayerEdit(posTarget, facing, stack)) {
            return EnumActionResult.FAIL;
         } else {
            if(facing != EnumFacing.WEST && facing != EnumFacing.EAST) {
               if(facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
                  if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
                     iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockPole.ORIENTATION, Integer.valueOf(2));
                  }
               } else {
                  iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockPole.ORIENTATION, Integer.valueOf(1));
               }
            } else {
               iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockPole.ORIENTATION, Integer.valueOf(0));
            }

            worldIn.setBlockState(posTarget, iblockstate1, 3);
            DMPHelper.playObjectSound(worldIn, pos, playerIn, this.block);
            stack.setCount(stack.getCount() - 1);
            return EnumActionResult.SUCCESS;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
