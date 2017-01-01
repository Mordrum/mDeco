package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockSofa;
import com.mordrum.mdeco.tileentity.DMPTileEntitySofa;
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
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class DMPRenderSofa extends TileEntitySpecialRenderer {
	private RenderManager renderManager;
   private final Minecraft mc = Minecraft.getMinecraft();
   public int jLast = 0;

   public DMPRenderSofa(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.doRender((DMPTileEntitySofa)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void doRender(DMPTileEntitySofa te, double x, double y, double z, float par6, int par7) {
      ItemStack stackBack = te.getStackInSlot(0);
      ItemStack stackBottom = te.getStackInSlot(1);
      if(stackBack != null || stackBottom != null) {
         Tessellator tessellator = Tessellator.getInstance();
         VertexBuffer vertexBuffer = tessellator.getBuffer();
         GL11.glPushMatrix();
         this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
         BlockRendererDispatcher blockRendererDispatcher = this.mc.getBlockRendererDispatcher();
         Block blockBack = null;
         IBlockState renderBlockBack = null;
         TextureAtlasSprite spriteBack = null;
         if(stackBack != null) {
            blockBack = Block.getBlockFromItem(stackBack.getItem());
            renderBlockBack = blockBack.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stackBack.getMetadata()));
            spriteBack = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockBack);
         }

         Block blockBottom = null;
         IBlockState renderBlockBottom = null;
         TextureAtlasSprite spriteBottom = null;
         if(stackBottom != null) {
            blockBottom = Block.getBlockFromItem(stackBottom.getItem());
            renderBlockBottom = blockBottom.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stackBottom.getMetadata()));
            spriteBottom = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockBottom);
         }

         GL11.glColor4f(0.8F, 0.8F, 0.8F, 0.8F);
         GL11.glDisable(2896);
         GL11.glTranslatef((float)x, (float)y, (float)z);
         vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
         DMPBlockSofa blockSofa = (DMPBlockSofa)te.getWorld().getBlockState(te.getPos()).getBlock();
         IBlockState stateSofa = blockSofa.getActualState(te.getWorld().getBlockState(te.getPos()), te.getWorld(), te.getPos());
         if(spriteBack != null) {
            this.renderCushionBack(vertexBuffer, stateSofa, spriteBack);
         }

         if(spriteBottom != null) {
            this.renderCushionBottom(vertexBuffer, stateSofa, spriteBottom);
         }

         tessellator.draw();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

   private void renderCushionBack(VertexBuffer vertexBuffer, IBlockState sofaState, TextureAtlasSprite spriteBack) {
      boolean bFore = sofaState.getValue(DMPBlockSofa.CONNECT_FORE);
      boolean bLeft = sofaState.getValue(DMPBlockSofa.CONNECT_LEFT);
      boolean bRight = sofaState.getValue(DMPBlockSofa.CONNECT_RIGHT);
      EnumFacing facing = sofaState.getValue(DMPBlockSofa.FACING);
      double pixel = 0.0625D;
      if(facing == EnumFacing.NORTH) {
         vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.SOUTH) {
         vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.WEST) {
         vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.5D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 4.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.EAST) {
         vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
         vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 12.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(11.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(12.0D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(12.0D)).endVertex();
               vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(14.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 4.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.5D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            }
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 0.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(4.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(2.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(0.5D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(15.5D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(4.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 15.5D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(16.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 16.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(16.0D)).endVertex();
            vertexBuffer.pos(pixel * 12.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBack.getInterpolatedU(14.0D), (double)spriteBack.getInterpolatedV(6.0D)).endVertex();
         }
      }

   }

   private void renderCushionBottom(VertexBuffer vertexBuffer, IBlockState sofaState, TextureAtlasSprite spriteBottom) {
      boolean bFore = sofaState.getValue(DMPBlockSofa.CONNECT_FORE);
      boolean bLeft = sofaState.getValue(DMPBlockSofa.CONNECT_LEFT);
      boolean bRight = sofaState.getValue(DMPBlockSofa.CONNECT_RIGHT);
      EnumFacing facing = sofaState.getValue(DMPBlockSofa.FACING);
      double pixel = 0.0625D;
      if(facing == EnumFacing.NORTH) {
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.SOUTH) {
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.WEST) {
         vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 15.9D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }
         } else {
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 15.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }
      } else if(facing == EnumFacing.EAST) {
         vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
         vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
         if(bFore) {
            if(bLeft) {
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 1.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }

            if(bRight) {
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.9D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
               vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 8.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
               vertexBuffer.pos(pixel * 0.1D, pixel * 6.0D, pixel * 15.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            }
         } else {
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bLeft) {
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 0.1D).tex((double)spriteBottom.getInterpolatedU(0.1D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 2.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }

         if(bRight) {
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(2.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(15.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 14.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 15.9D).tex((double)spriteBottom.getInterpolatedU(15.9D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(14.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         } else {
            vertexBuffer.pos(pixel * 2.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
            vertexBuffer.pos(pixel * 2.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(2.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 8.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(8.0D)).endVertex();
            vertexBuffer.pos(pixel * 1.0D, pixel * 6.0D, pixel * 14.0D).tex((double)spriteBottom.getInterpolatedU(1.0D), (double)spriteBottom.getInterpolatedV(6.0D)).endVertex();
         }
      }

   }
}
