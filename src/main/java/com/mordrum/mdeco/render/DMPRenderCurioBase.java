package com.mordrum.mdeco.render;

import com.mordrum.mdeco.tileentity.DMPTileEntityCurioBase;
import com.mordrum.mdeco.block.DMPBlockCurioBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderCurioBase extends TileEntitySpecialRenderer {
	private RenderManager renderManager;
   public int jLast = 0;

   public DMPRenderCurioBase(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityCurioBase)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   public void render(DMPTileEntityCurioBase te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockCurioBase) {
         EnumFacing facing = myBlockState.getValue(DMPBlockCurioBase.FACING);

         for(int i = 0; i < 4; ++i) {
            if(te.getStackInSlot(i) != null) {
               GL11.glPushMatrix();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               GL11.glTranslated(x, y, z);
               GL11.glPushMatrix();
               GL11.glScalef(0.5F, 0.5F, 0.5F);
               GL11.glTranslatef(0.5F, 0.0F, 0.5F);
               GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslated(0.0D, 0.0D, 0.0D);
               EntityItem customItem = new EntityItem(te.getWorld(), (double)te.getPos().getX(), (double)te.getPos().getY(), (double)te.getPos().getZ(), te.getStackInSlot(i));
               customItem.hoverStart = 0.0F;
               customItem.posX = (double)te.getPos().getX();
               customItem.posY = (double)(te.getPos().getY() + 2);
               customItem.posZ = (double)te.getPos().getZ();
               GL11.glScalef(0.9F, 0.9F, 0.9F);
               if(i == 0) {
                  this.renderNorthItem(customItem, facing, 0.125F);
               } else if(i == 1) {
                  this.renderWestItem(customItem, facing, 0.125F);
               } else if(i == 2) {
                  this.renderEastItem(customItem, facing, 0.125F);
               } else if(i == 3) {
                  this.renderSouthItem(customItem, facing, 0.125F);
               }

               GL11.glPopMatrix();
               GL11.glEnable(3008);
               GL11.glPopMatrix();
            }
         }

         this.renderInlay(te, x, y, z);
      }
   }

   private void renderNorthItem(EntityItem customItem, EnumFacing facing, float height) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.5F, height, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.5F, height, 1.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.0F, height, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(1.0F, height, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderSouthItem(EntityItem customItem, EnumFacing facing, float height) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.5F, height, 1.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.5F, height, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(1.0F, height, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.0F, height, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderWestItem(EntityItem customItem, EnumFacing facing, float height) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.0F, height, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(1.0F, height, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.5F, height, 1.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.5F, height, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderEastItem(EntityItem customItem, EnumFacing facing, float height) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(1.0F, height, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.0F, height, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.5F, height, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.5F, height, 1.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderInlay(DMPTileEntityCurioBase te, double x, double y, double z) {
      ItemStack stack = te.getStackInSlot(4);
      if(stack != null) {
         Minecraft mc = Minecraft.getMinecraft();
         Tessellator tessellator = Tessellator.getInstance();
         VertexBuffer vertexBuffer = tessellator.getBuffer();
         GL11.glPushMatrix();
         this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
         Block block = Block.getBlockFromItem(stack.getItem());
         IBlockState renderBlockState = Block.getStateById(Block.getIdFromBlock(block));
         if(block == Blocks.STAINED_GLASS || block == Blocks.STAINED_GLASS_PANE || block == Blocks.WOOL || block == Blocks.CARPET || block == Blocks.STAINED_HARDENED_CLAY) {
            renderBlockState = block.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(stack.getMetadata()));
         }

         BlockRendererDispatcher blockRendererDispatcher = mc.getBlockRendererDispatcher();
         TextureAtlasSprite sprite = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockState);
         GL11.glColor4f(0.75F, 0.75F, 0.75F, 1.0F);
         GL11.glDisable(2896);
         GL11.glTranslatef((float)x, (float)y, (float)z);
         vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
         this.renderNorthSouthInlay(vertexBuffer, sprite, 2.5F, 12.0F, 2.0F);
         this.renderNorthSouthInlay(vertexBuffer, sprite, 2.5F, 12.0F, 14.0F);
         this.renderEastWestInlay(vertexBuffer, sprite, 2.0F, 12.0F, 2.5F);
         this.renderEastWestInlay(vertexBuffer, sprite, 14.0F, 12.0F, 2.5F);
         tessellator.draw();
         GL11.glPopMatrix();
      }
   }

   private void renderEastWestInlay(VertexBuffer vertexBuffer, TextureAtlasSprite sprite, float startX, float startY, float startZ) {
      double pixel = 0.0625D;
      double paneHeight = (double)((sprite.getMaxV() - sprite.getMinV()) / 16.0F * 3.0F);
      double paneWidth = (double)((sprite.getMaxU() - sprite.getMinU()) / 16.0F * 11.0F);
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)(startZ + 11.0F)).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)(startZ + 11.0F)).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)(startZ + 11.0F)).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)(startZ + 11.0F)).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
   }

   private void renderNorthSouthInlay(VertexBuffer vertexBuffer, TextureAtlasSprite sprite, float startX, float startY, float startZ) {
      double pixel = 0.0625D;
      double paneHeight = (double)((sprite.getMaxV() - sprite.getMinV()) / 16.0F * 3.0F);
      double paneWidth = (double)((sprite.getMaxU() - sprite.getMinU()) / 16.0F * 11.0F);
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)(startX + 11.0F), pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)(startX + 11.0F), pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)(startX + 11.0F), pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV() - paneHeight).endVertex();
      vertexBuffer.pos(pixel * (double)(startX + 11.0F), pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU(), (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)(startY + 3.0F), pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV()).endVertex();
      vertexBuffer.pos(pixel * (double)startX, pixel * (double)startY, pixel * (double)startZ).tex((double)sprite.getMinU() + paneWidth, (double)sprite.getMaxV() - paneHeight).endVertex();
   }
}
