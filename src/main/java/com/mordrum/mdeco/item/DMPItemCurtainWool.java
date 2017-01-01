package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockCurtain;
import com.mordrum.mdeco.block.DMPBlockCurtainRod;
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

public class DMPItemCurtainWool extends ItemBlock {
   private DMPBlockCurtain decorationBlock;

   public DMPItemCurtainWool(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockCurtain)block;
   }

   public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      BlockPos posTarget = null;
      if(facing == EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         if(facing == EnumFacing.DOWN) {
            if(worldIn.getBlockState(pos).getBlock() instanceof DMPBlockCurtain) {
               facing = (EnumFacing)worldIn.getBlockState(pos).getValue(DMPBlockCurtain.FACING);
               posTarget = pos.down();
            } else {
               if(!(worldIn.getBlockState(pos).getBlock() instanceof DMPBlockCurtainRod)) {
                  return EnumActionResult.FAIL;
               }

               facing = (EnumFacing)worldIn.getBlockState(pos).getValue(DMPBlockCurtainRod.FACING);
               posTarget = pos.down();
            }
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
            BlockPos posAbove = posTarget.up();
            Block blockAbove = worldIn.getBlockState(posAbove).getBlock();
            if(blockAbove != null) {
               if(blockAbove instanceof DMPBlockCurtain) {
                  facing = (EnumFacing)worldIn.getBlockState(posAbove).getValue(DMPBlockCurtain.FACING);
               } else if(blockAbove instanceof DMPBlockCurtainRod) {
                  facing = (EnumFacing)worldIn.getBlockState(posAbove).getValue(DMPBlockCurtainRod.FACING);
               }
            }

            if(playerIn.canPlayerEdit(posTarget, facing, stack)) {
               IBlockState iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockCurtain.FACING, facing);
               worldIn.setBlockState(posTarget, iblockstate1, 3);
               DMPHelper.playObjectSound(worldIn, pos, playerIn, this.block);
               stack.setCount(stack.getCount() - 1);
               return EnumActionResult.SUCCESS;
            } else {
               return EnumActionResult.FAIL;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      }
   }
}
