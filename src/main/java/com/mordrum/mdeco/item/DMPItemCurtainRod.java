package com.mordrum.mdeco.item;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DMPItemCurtainRod extends ItemBlock {
   private DMPBlockCurtainRod decorationBlock;

   public DMPItemCurtainRod(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockCurtainRod)block;
   }

   public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      BlockPos posTarget = null;
      if(facing == EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else if(facing == EnumFacing.DOWN) {
         return EnumActionResult.FAIL;
      } else {
         if(facing == EnumFacing.NORTH) {
            posTarget = pos.north();
         } else if(facing == EnumFacing.SOUTH) {
            posTarget = pos.south();
         } else if(facing == EnumFacing.WEST) {
            posTarget = pos.west();
         } else if(facing == EnumFacing.EAST) {
            posTarget = pos.east();
         }

         if(posTarget != null && worldIn.isAirBlock(posTarget)) {
            int direction = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumFacing playerFacing = EnumFacing.getHorizontal(direction);
            if(playerFacing != facing.getOpposite()) {
               playerFacing = playerFacing.getOpposite();
            }

            if(playerIn.canPlayerEdit(posTarget, facing.getOpposite(), stack)) {
               IBlockState iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockCurtainRod.FACING, playerFacing);
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
