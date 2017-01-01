package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockCabinetBase;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetBase;
import net.minecraft.block.Block;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderCabinetBase extends TileEntitySpecialRenderer {
	private RenderManager renderManager;
   private final Minecraft mc = Minecraft.getMinecraft();
   public int jLast = 0;

   public DMPRenderCabinetBase(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityCabinetBase)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void render(DMPTileEntityCabinetBase te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockCabinetBase) {
         EnumFacing facing = myBlockState.getValue(DMPBlockCabinetBase.FACING);
         Block block = te.getCountertopTile();
         if(block != null) {
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer worldrenderer = tessellator.getBuffer();
            BlockRendererDispatcher blockRendererDispatcher = this.mc.getBlockRendererDispatcher();
            IBlockState renderBlockState = block.getStateFromMeta(facing.getHorizontalIndex());
            TextureAtlasSprite sprite = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockState);
            this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glTranslatef((float)x, (float)y, (float)z);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            this.renderCountertop(worldrenderer, sprite, facing);
            tessellator.draw();
            GL11.glPopMatrix();
         }

      }
   }

   private void renderCountertop(VertexBuffer vertextBuffer, TextureAtlasSprite sprite, EnumFacing facing) {
      double pixel = 0.0625D;
      if(facing == EnumFacing.NORTH) {
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMaxU(), (double)sprite.getMaxV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMaxU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMinU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      } else if(facing == EnumFacing.SOUTH) {
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMaxU(), (double)sprite.getMaxV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMaxU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMinU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      } else if(facing == EnumFacing.WEST) {
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMaxU(), (double)sprite.getMaxV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMaxU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMinU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      } else if(facing == EnumFacing.EAST) {
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMaxU(), (double)sprite.getMaxV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getMaxU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMinU(), (double)sprite.getMinV()).endVertex();
         vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      }

      vertextBuffer.pos(pixel * 0.0D, pixel * 15.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(15.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(15.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 15.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 15.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(1.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(1.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 15.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 15.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(15.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(15.0D)).endVertex();
      vertextBuffer.pos(pixel * 0.0D, pixel * 15.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(16.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 15.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 0.0D).tex((double)sprite.getInterpolatedU(16.0D), (double)sprite.getInterpolatedV(1.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 16.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(1.0D)).endVertex();
      vertextBuffer.pos(pixel * 16.0D, pixel * 15.0D, pixel * 16.0D).tex((double)sprite.getInterpolatedU(0.0D), (double)sprite.getInterpolatedV(0.0D)).endVertex();
   }
}
