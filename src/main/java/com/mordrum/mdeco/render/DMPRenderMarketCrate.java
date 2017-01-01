package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockMarketCrate;
import com.mordrum.mdeco.core.DMPMapDyeItemColor;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketCrate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderMarketCrate extends TileEntitySpecialRenderer {
   private RenderItem renderItem;
   private RenderManager renderManager;
   public int jLast = 0;

   public DMPRenderMarketCrate(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
      this.renderItem = ri;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityMarketCrate)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   public void render(DMPTileEntityMarketCrate te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockMarketCrate) {
         EnumFacing facing = (EnumFacing)myBlockState.getValue(DMPBlockMarketCrate.FACING);
         ItemStack stackDye1 = te.getStackInSlot(13);
         ItemStack stackDye2 = te.getStackInSlot(14);

         for(int text = 0; text < 13; ++text) {
            if(te.getStackInSlot(text) != null) {
               GL11.glPushMatrix();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               GL11.glTranslated(x, y, z);
               GL11.glPushMatrix();
               GL11.glScalef(0.5F, 0.5F, 0.5F);
               GL11.glTranslatef(1.5F, 0.3F, 0.5F);
               GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(-0.5F, 0.0F, 0.5F);
               GL11.glTranslated(0.0D, 0.0D, 0.0D);
               EntityItem customItem = new EntityItem(te.getWorld(), (double)te.getPos().getX(), (double)te.getPos().getY(), (double)te.getPos().getZ(), te.getStackInSlot(text));
               customItem.hoverStart = 0.0F;
               customItem.posX = (double)te.getPos().getX();
               customItem.posY = (double)(te.getPos().getY() + 2);
               customItem.posZ = (double)te.getPos().getZ();
               GL11.glScalef(0.9F, 0.9F, 0.9F);
               GL11.glTranslatef(0.0F, 0.0F, 0.3F);
               if(facing == EnumFacing.SOUTH) {
                  this.renderSouth(text);
               }

               if(facing == EnumFacing.WEST) {
                  this.renderWest(text);
               }

               if(facing == EnumFacing.NORTH) {
                  this.renderNorth(text);
               }

               if(facing == EnumFacing.EAST) {
                  this.renderEast(text);
               }

               GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
               GL11.glDisable(2896);
               this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
               GL11.glPopMatrix();
               GL11.glDisable(3008);
               GL11.glPushMatrix();
               GL11.glTranslatef(0.5F, 1.8F, 0.5F);
               GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
               GL11.glPopMatrix();
               GL11.glEnable(3008);
               GL11.glPopMatrix();
            }
         }

         String var17 = te.getDisplayText(1);
         if(var17 != null && var17.length() > 0 && stackDye1 != null) {
            this.renderDisplayText(1, x, y, z, facing, var17, stackDye1);
            this.renderDisplayText(1, x, y, z, facing, var17, stackDye1);
         }

         var17 = te.getDisplayText(2);
         if(var17 != null && var17.length() > 0 && stackDye2 != null) {
            this.renderDisplayText(2, x, y, z, facing, var17, stackDye2);
            this.renderDisplayText(2, x, y, z, facing, var17, stackDye2);
         }

      }
   }

   private void renderDisplayText(int row, double x, double y, double z, EnumFacing facing, String text, ItemStack dyeItemStack) {
      float f1 = 0.6666667F;
      float f3 = 0.015625F * f1;
      float height = 0.225F;
      if(row == 1) {
         height = 0.425F;
      }

      FontRenderer fontrenderer = this.getFontRenderer();
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glTranslated(x, y, z);
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.5F, height, 0.9063F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, height, -0.09372F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, height, 0.9063F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.5F, height, -0.09372F);
      }

      GL11.glScalef(f3, -f3, f3);
      GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
      fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, 5, DMPMapDyeItemColor.getColorFromDyeItem(dyeItemStack));
      GL11.glPopMatrix();
   }

   private void renderSouth(int item) {
      GL11.glTranslatef(0.0F, 0.0F, -0.58F);
      switch(item) {
      case 0:
         GlStateManager.rotate(22.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.3F, 0.0F, 0.55F);
         break;
      case 1:
         GlStateManager.rotate(225.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.5F, 0.0F, -1.0F);
         break;
      case 2:
         GlStateManager.rotate(100.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.65F, 0.0F, -1.0F);
         break;
      case 3:
         GlStateManager.rotate(112.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.3F, 0.0F, -0.0F);
         break;
      case 4:
         GL11.glTranslatef(-0.0F, 0.0F, -0.0F);
         break;
      case 5:
         GlStateManager.rotate(195.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.6F, 0.0F, -0.4F);
         break;
      case 6:
         GlStateManager.rotate(325.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.2F, 0.0F, -0.8F);
         break;
      case 7:
         GlStateManager.rotate(65.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.2F, 0.0F, -0.5F);
         break;
      case 8:
         GlStateManager.rotate(245.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.0F, 0.0F, 0.25F);
         break;
      case 9:
         GlStateManager.rotate(95.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, 0.15F, -0.0F);
         break;
      case 10:
         GlStateManager.rotate(305.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.25F, 0.15F, 0.3F);
         break;
      case 11:
         GlStateManager.rotate(200.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.25F, 0.15F, -0.2F);
         break;
      case 12:
         GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.3F, 0.15F, -0.15F);
      }

      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
   }

   private void renderWest(int item) {
      GL11.glTranslatef(0.28F, 0.0F, -0.25F);
      switch(item) {
      case 0:
         GlStateManager.rotate(22.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.55F, 0.0F, 0.3F);
         break;
      case 1:
         GlStateManager.rotate(225.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(1.0F, 0.0F, 0.5F);
         break;
      case 2:
         GlStateManager.rotate(100.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(1.0F, 0.0F, -0.65F);
         break;
      case 3:
         GlStateManager.rotate(112.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, -0.3F);
         break;
      case 4:
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         break;
      case 5:
         GlStateManager.rotate(195.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.4F, 0.0F, 0.6F);
         break;
      case 6:
         GlStateManager.rotate(325.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.8F, 0.0F, 0.2F);
         break;
      case 7:
         GlStateManager.rotate(65.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.5F, 0.0F, 0.2F);
         break;
      case 8:
         GlStateManager.rotate(245.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.25F, 0.0F, 0.0F);
         break;
      case 9:
         GlStateManager.rotate(95.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.15F, -0.5F);
         break;
      case 10:
         GlStateManager.rotate(305.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.3F, 0.15F, 0.25F);
         break;
      case 11:
         GlStateManager.rotate(200.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.2F, 0.15F, -0.25F);
         break;
      case 12:
         GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.15F, 0.15F, -0.3F);
      }

      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
   }

   private void renderNorth(int item) {
      switch(item) {
      case 0:
         GlStateManager.rotate(22.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.3F, 0.0F, -0.55F);
         break;
      case 1:
         GlStateManager.rotate(225.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, 0.0F, 1.0F);
         break;
      case 2:
         GlStateManager.rotate(100.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.65F, 0.0F, 1.0F);
         break;
      case 3:
         GlStateManager.rotate(112.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.3F, 0.0F, 0.0F);
         break;
      case 4:
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         break;
      case 5:
         GlStateManager.rotate(195.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.6F, 0.0F, 0.4F);
         break;
      case 6:
         GlStateManager.rotate(325.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.2F, 0.0F, 0.8F);
         break;
      case 7:
         GlStateManager.rotate(65.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.2F, 0.0F, 0.5F);
         break;
      case 8:
         GlStateManager.rotate(245.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, -0.25F);
         break;
      case 9:
         GlStateManager.rotate(95.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.5F, 0.15F, 0.0F);
         break;
      case 10:
         GlStateManager.rotate(305.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.25F, 0.15F, -0.3F);
         break;
      case 11:
         GlStateManager.rotate(200.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.25F, 0.15F, 0.2F);
         break;
      case 12:
         GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.3F, 0.15F, 0.15F);
      }

   }

   private void renderEast(int item) {
      GL11.glTranslatef(-0.25F, 0.0F, -0.32F);
      switch(item) {
      case 0:
         GlStateManager.rotate(22.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.55F, 0.0F, -0.3F);
         break;
      case 1:
         GlStateManager.rotate(225.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, 0.0F, -0.5F);
         break;
      case 2:
         GlStateManager.rotate(100.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, 0.0F, 0.65F);
         break;
      case 3:
         GlStateManager.rotate(112.5F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
         break;
      case 4:
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         break;
      case 5:
         GlStateManager.rotate(195.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.4F, 0.0F, -0.6F);
         break;
      case 6:
         GlStateManager.rotate(325.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.8F, 0.0F, -0.2F);
         break;
      case 7:
         GlStateManager.rotate(65.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, 0.0F, -0.2F);
         break;
      case 8:
         GlStateManager.rotate(245.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.25F, 0.0F, 0.0F);
         break;
      case 9:
         GlStateManager.rotate(95.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.15F, 0.5F);
         break;
      case 10:
         GlStateManager.rotate(305.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.3F, 0.15F, -0.25F);
         break;
      case 11:
         GlStateManager.rotate(200.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.2F, 0.15F, 0.25F);
         break;
      case 12:
         GlStateManager.rotate(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.15F, 0.15F, 0.3F);
      }

      GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
   }
}
