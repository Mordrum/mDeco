package com.mordrum.mdeco.item;

import com.mordrum.mdeco.block.DMPBlockKitchenTableSetting;
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

public class DMPItemKitchenTableSetting extends ItemBlock {
   private DMPBlockKitchenTableSetting decorationBlock;

   public DMPItemKitchenTableSetting(Block block) {
      super(block);
      this.setUnlocalizedName(block.getUnlocalizedName().substring(5));
      this.decorationBlock = (DMPBlockKitchenTableSetting)block;
   }

   public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      BlockPos posTarget = null;
      if(facing == EnumFacing.UP && worldIn.isSideSolid(pos, EnumFacing.UP)) {
         posTarget = pos.up();
         if(posTarget != null && worldIn.isAirBlock(posTarget)) {
            int direction = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumFacing enumFacing = EnumFacing.getHorizontal(direction);
            if(playerIn.canPlayerEdit(posTarget, facing, stack)) {
               IBlockState iblockstate1 = this.decorationBlock.getDefaultState().withProperty(DMPBlockKitchenTableSetting.FACING, enumFacing);
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
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
