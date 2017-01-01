package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockPoleSign;
import com.mordrum.mdeco.core.DMPMapDyeItemColor;
import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderPoleSign extends TileEntitySpecialRenderer {
	public int jLast = 0;

   public DMPRenderPoleSign(RenderManager rm, RenderItem ri) {
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityPoleSign)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void render(DMPTileEntityPoleSign te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockPoleSign) {
         EnumFacing facing = myBlockState.getValue(DMPBlockPoleSign.FACING);
         ItemStack stackDye = te.getStackInSlot(0);
         String text = te.getSignText();
         if(text != null && text.length() > 0 && stackDye != null) {
            this.renderSignText(x, y, z, facing, text, false, stackDye);
            this.renderSignText(x, y, z, facing, text, true, stackDye);
         }

      }
   }

   private void renderSignText(double x, double y, double z, EnumFacing facing, String text, boolean flip, ItemStack dyeItemStack) {
      float f1 = 0.6666667F;
      float f3 = 0.015625F * f1;
      float height = 0.6F;
      FontRenderer fontrenderer = this.getFontRenderer();
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glTranslated(x, y, z);
      GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
      if(facing == EnumFacing.NORTH) {
         if(flip) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.78F, height, 1.02F);
         } else {
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.78F, height, -0.98F);
         }
      } else if(facing == EnumFacing.SOUTH) {
         if(flip) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-1.22F, height, 1.02F);
         } else {
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.22F, height, -0.98F);
         }
      } else if(facing == EnumFacing.WEST) {
         if(flip) {
            GL11.glTranslatef(0.78F, height, 1.02F);
         } else {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.78F, height, -0.98F);
         }
      } else if(facing == EnumFacing.EAST) {
         if(flip) {
            GL11.glTranslatef(1.22F, height, 1.02F);
         } else {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-1.22F, height, -0.98F);
         }
      }

      GL11.glScalef(f3, -f3, f3);
      GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
      fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, 5, DMPMapDyeItemColor.getColorFromDyeItem(dyeItemStack));
      GL11.glPopMatrix();
   }
}
