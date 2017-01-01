package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockShopSign;
import com.mordrum.mdeco.core.DMPMapDyeItemColor;
import com.mordrum.mdeco.tileentity.DMPTileEntityShopSign;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderShopSign extends TileEntitySpecialRenderer {
   private RenderItem renderItem;
   private RenderManager renderManager;
   private final Minecraft mc = Minecraft.getMinecraft();
   public int jLast = 0;

   public DMPRenderShopSign(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
      this.renderItem = ri;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityShopSign)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void render(DMPTileEntityShopSign te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockShopSign) {
         EnumFacing facing = (EnumFacing)myBlockState.getValue(DMPBlockShopSign.FACING);
         ItemStack stackImage = te.getStackInSlot(0);
         ItemStack stackDye1 = te.getStackInSlot(1);
         ItemStack stackDye2 = te.getStackInSlot(2);
         boolean imageOnly = stackDye1 == null && stackDye2 == null;
         if(stackImage != null) {
            Block text = Block.getBlockFromItem(stackImage.getItem());
            if(text == null) {
               EntityItem tessellator = new EntityItem(te.getWorld(), (double)te.getPos().getX(), (double)te.getPos().getY(), (double)te.getPos().getZ(), te.getStackInSlot(0));
               tessellator.posX = (double)te.getPos().getX();
               tessellator.posY = (double)te.getPos().getY();
               tessellator.posZ = (double)te.getPos().getZ();
               tessellator.hoverStart = 0.0F;
               this.renderItemFace(imageOnly, x, y, z, facing, tessellator, false);
               this.renderItemFace(imageOnly, x, y, z, facing, tessellator, true);
            } else {
               Tessellator tessellator1 = Tessellator.getInstance();
               VertexBuffer worldrenderer = tessellator1.getBuffer();
               BlockRendererDispatcher blockRendererDispatcher = this.mc.getBlockRendererDispatcher();
               IBlockState renderBlockState = text.getStateFromMeta(stackImage.getItemDamage());
               TextureAtlasSprite sprite = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockState);
               this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
               GL11.glPushMatrix();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               GL11.glTranslatef((float)x, (float)y, (float)z);
               GL11.glScalef(0.5F, 0.5F, 0.5F);
               worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
               this.renderBlockFaces(imageOnly, worldrenderer, sprite, facing);
               tessellator1.draw();
               GL11.glPopMatrix();
            }
         }

         String text1 = te.getSignText(1);
         if(text1 != null && text1.length() > 0 && stackDye1 != null) {
            this.renderSignText(stackImage == null, 1, x, y, z, facing, text1, false, stackDye1);
            this.renderSignText(stackImage == null, 1, x, y, z, facing, text1, true, stackDye1);
         }

         text1 = te.getSignText(2);
         if(text1 != null && text1.length() > 0 && stackDye2 != null) {
            this.renderSignText(stackImage == null, 2, x, y, z, facing, text1, false, stackDye2);
            this.renderSignText(stackImage == null, 2, x, y, z, facing, text1, true, stackDye2);
         }

      }
   }

   private void renderSignText(boolean textOnly, int row, double x, double y, double z, EnumFacing facing, String text, boolean flip, ItemStack dyeItemStack) {
      float f1 = 0.6666667F;
      float f3 = 0.015625F * f1;
      float height = 0.35F;
      if(row == 1) {
         height = 0.48F;
      }

      if(textOnly) {
         height += 0.125F;
      }

      FontRenderer fontrenderer = this.getFontRenderer();
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glTranslated(x, y, z);
      GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
      if(facing != EnumFacing.WEST && facing != EnumFacing.EAST) {
         if(flip) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-1.0F, height, 1.04F);
         } else {
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.0F, height, -0.96F);
         }
      } else if(flip) {
         GL11.glTranslatef(1.0F, height, 1.04F);
      } else {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, height, -0.96F);
      }

      GL11.glScalef(f3, -f3, f3);
      GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
      fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, 5, DMPMapDyeItemColor.getColorFromDyeItem(dyeItemStack));
      GL11.glPopMatrix();
   }

   private void renderItemFace(boolean imageOnly, double x, double y, double z, EnumFacing facing, EntityItem customItem, boolean flip) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glTranslated(x, y, z);
      if(!imageOnly) {
         GL11.glScalef(0.5F, 0.5F, 0.5F);
      }

      if(imageOnly) {
         if(facing != EnumFacing.WEST && facing != EnumFacing.EAST) {
            if(flip) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(-0.5F, 0.0F, 0.55F);
            } else {
               GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(0.5F, 0.0F, -0.45F);
            }
         } else if(flip) {
            GL11.glTranslatef(0.5F, 0.0F, 0.55F);
         } else {
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, 0.0F, -0.45F);
         }
      } else if(facing != EnumFacing.WEST && facing != EnumFacing.EAST) {
         if(flip) {
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-1.0F, 0.7F, 1.1F);
         } else {
            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.0F, 0.7F, -0.9F);
         }
      } else if(flip) {
         GL11.glTranslatef(1.0F, 0.7F, 1.1F);
      } else {
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, 0.7F, -0.9F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
      GL11.glPopMatrix();
   }

   private void renderBlockFaces(boolean imageOnly, VertexBuffer vertexBuffer, TextureAtlasSprite sprite, EnumFacing facing) {
      float pixel = 0.0625F;
      if(imageOnly) {
         if(facing != EnumFacing.EAST && facing != EnumFacing.WEST) {
            vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 6.5D, (double)pixel * 24.5D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 22.5D, (double)pixel * 24.5D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 22.5D, (double)pixel * 8.5D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 6.5D, (double)pixel * 8.5D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 6.5D, (double)(pixel * 8.0F)).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 22.5D, (double)(pixel * 8.0F)).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 22.5D, (double)(pixel * 24.0F)).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 6.5D, (double)(pixel * 24.0F)).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         } else {
            vertexBuffer.pos((double)(pixel * 8.0F), (double)pixel * 6.5D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)(pixel * 8.0F), (double)pixel * 22.5D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)(pixel * 24.0F), (double)pixel * 22.5D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)(pixel * 24.0F), (double)pixel * 6.5D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 24.5D, (double)pixel * 6.5D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 24.5D, (double)pixel * 22.5D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 8.5D, (double)pixel * 22.5D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
            vertexBuffer.pos((double)pixel * 8.5D, (double)pixel * 6.5D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         }
      } else if(facing != EnumFacing.EAST && facing != EnumFacing.WEST) {
         vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 15.25D, (double)pixel * 20.5D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 23.25D, (double)pixel * 20.5D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 23.25D, (double)pixel * 12.5D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 14.98D, (double)pixel * 15.25D, (double)pixel * 12.5D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 15.25D, (double)(pixel * 12.0F)).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 23.25D, (double)(pixel * 12.0F)).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 23.25D, (double)(pixel * 20.0F)).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 17.02D, (double)pixel * 15.25D, (double)(pixel * 20.0F)).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      } else {
         vertexBuffer.pos((double)(pixel * 12.0F), (double)pixel * 15.25D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)(pixel * 12.0F), (double)pixel * 23.25D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)(pixel * 20.0F), (double)pixel * 23.25D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)(pixel * 20.0F), (double)pixel * 15.25D, (double)pixel * 14.98D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 20.5D, (double)pixel * 15.25D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 20.5D, (double)pixel * 23.25D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 12.5D, (double)pixel * 23.25D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
         vertexBuffer.pos((double)pixel * 12.5D, (double)pixel * 15.25D, (double)pixel * 17.02D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      }

   }
}
