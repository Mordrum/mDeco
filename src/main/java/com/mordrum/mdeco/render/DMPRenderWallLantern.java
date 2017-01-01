package com.mordrum.mdeco.render;

import com.mordrum.mdeco.tileentity.DMPTileEntityWallLantern;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class DMPRenderWallLantern extends TileEntitySpecialRenderer {
	private RenderManager renderManager;
   private final Minecraft mc = Minecraft.getMinecraft();
   public int jLast = 0;

   public DMPRenderWallLantern(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.doRender((DMPTileEntityWallLantern)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void doRender(DMPTileEntityWallLantern te, double x, double y, double z, float par6, int par7) {
      ItemStack stack = te.getStackInSlot(0);
      if(stack != null) {
         Tessellator tessellator = Tessellator.getInstance();
         VertexBuffer worldrenderer = tessellator.getBuffer();
         GL11.glPushMatrix();
         this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
         Block block = Block.getBlockFromItem(stack.getItem());
         IBlockState renderBlockState = Block.getStateById(Block.getIdFromBlock(block));
         if(block == Blocks.STAINED_GLASS || block == Blocks.STAINED_GLASS_PANE || block == Blocks.WOOL || block == Blocks.CARPET || block == Blocks.STAINED_HARDENED_CLAY) {
            renderBlockState = block.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stack.getMetadata()));
         }

         BlockRendererDispatcher blockRendererDispatcher = this.mc.getBlockRendererDispatcher();
         TextureAtlasSprite sprite = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockState);
         GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
         GL11.glDisable(2896);
         GL11.glTranslatef((float)x, (float)y, (float)z);
         worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         this.renderFaces(worldrenderer, sprite);
         tessellator.draw();
         GL11.glPopMatrix();
      }
   }

   private void renderFaces(VertexBuffer vertexBuffer, TextureAtlasSprite sprite) {
      this.renderEastWestPanel(vertexBuffer, sprite, 5.0F, 8.0F, 5.25F);
      this.renderEastWestPanel(vertexBuffer, sprite, 5.0F, 1.5F, 5.25F);
      this.renderEastWestPanel(vertexBuffer, sprite, 11.0F, 8.0F, 5.25F);
      this.renderEastWestPanel(vertexBuffer, sprite, 11.0F, 1.5F, 5.25F);
      this.renderNorthSouthPanel(vertexBuffer, sprite, 5.25F, 8.0F, 5.0F);
      this.renderNorthSouthPanel(vertexBuffer, sprite, 5.25F, 1.5F, 5.0F);
      this.renderNorthSouthPanel(vertexBuffer, sprite, 5.25F, 8.0F, 11.0F);
      this.renderNorthSouthPanel(vertexBuffer, sprite, 5.25F, 1.5F, 11.0F);
   }

   private void renderEastWestPanel(VertexBuffer vertexBuffer, TextureAtlasSprite sprite, float startX, float startY, float startZ) {
      double pixel = 0.0625D;
      double paneHeight = (double)((sprite.getMaxV() - sprite.getMinV()) / 16.0F) * 1.5D;
      double paneWidth = (double)((sprite.getMaxU() - sprite.getMinU()) / 16.0F) * 5.5D;
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * ((double)startZ + 5.5D)).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * ((double)startZ + 5.5D)).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * ((double)startZ + 5.5D)).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * ((double)startZ + 5.5D)).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
   }

   private void renderNorthSouthPanel(VertexBuffer vertexBuffer, TextureAtlasSprite sprite, float startX, float startY, float startZ) {
      double pixel = 0.0625D;
      double paneHeight = (double)((sprite.getMaxV() - sprite.getMinV()) / 16.0F) * 1.5D;
      double paneWidth = (double)((sprite.getMaxU() - sprite.getMinU()) / 16.0F) * 5.5D;
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * ((double)startX + 5.5D), pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * ((double)startX + 5.5D), pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * ((double)startX + 5.5D), pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * ((double)startX + 5.5D), pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * ((double)startY + 1.5D), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
   }
}
