package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockKitchenTableSetting;
import com.mordrum.mdeco.tileentity.DMPTileEntityKitchenTableSetting;
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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderKitchenTableSetting extends TileEntitySpecialRenderer {
   private RenderItem renderItem;
   private RenderManager renderManager;
   public int jLast = 0;

   public DMPRenderKitchenTableSetting(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
      this.renderItem = ri;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityKitchenTableSetting)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   private void render(DMPTileEntityKitchenTableSetting te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockKitchenTableSetting) {
         EnumFacing facing = (EnumFacing)myBlockState.getValue(DMPBlockKitchenTableSetting.FACING);
         if(te.getStackInSlot(0) != null) {
            EntityItem napkinBlockStack = new EntityItem(te.getWorld(), (double)te.getPos().getX(), (double)te.getPos().getY(), (double)te.getPos().getZ(), te.getStackInSlot(0));
            napkinBlockStack.hoverStart = 0.0F;
            napkinBlockStack.posX = (double)te.getPos().getX();
            napkinBlockStack.posY = (double)te.getPos().getY();
            napkinBlockStack.posZ = (double)te.getPos().getZ();
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3008);
            GL11.glDisable(2896);
            GL11.glTranslatef((float)(x + 0.5D), (float)y, (float)(z + 0.5D));
            switch(facing.ordinal()) {
            case 1:
            default:
               break;
            case 2:
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 3:
               GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 4:
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.145F, -0.63F, 0.06F);
            GL11.glScalef(0.9F, 0.9F, 0.9F);
            this.renderManager.doRenderEntity(napkinBlockStack, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
            GL11.glEnable(2896);
            GL11.glEnable(3008);
            GL11.glPopMatrix();
         }

         ItemStack napkinBlockStack1 = te.getStackInSlot(1);
         if(napkinBlockStack1 != null) {
            Minecraft mc = Minecraft.getMinecraft();
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer worldrenderer = tessellator.getBuffer();
            this.renderManager.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Block block = Block.getBlockFromItem(napkinBlockStack1.getItem());
            if(block == Blocks.WOOL || block == Blocks.CARPET) {
               IBlockState renderBlockState = block.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(napkinBlockStack1.getMetadata()));
               BlockRendererDispatcher blockRendererDispatcher = mc.getBlockRendererDispatcher();
               TextureAtlasSprite sprite = blockRendererDispatcher.getBlockModelShapes().getTexture(renderBlockState);
               GL11.glPushMatrix();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3008);
               GL11.glDisable(2896);
               GL11.glTranslatef((float)x, (float)y, (float)z);
               worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
               this.renderNapkin(worldrenderer, sprite, facing);
               tessellator.draw();
               GL11.glEnable(2896);
               GL11.glEnable(3008);
               GL11.glPopMatrix();
            }
         }

      }
   }

   private void renderNapkin(VertexBuffer vertexBuffer, TextureAtlasSprite sprite, EnumFacing facing) {
      double zz = 7.0D;
      double xx = 4.5D;
      double pixel = 0.0625D;
      double height = 5.0E-5D;
      double startX = 10.5D;
      double startZ = 8.5D;
      switch(facing.ordinal()) {
      case 1:
      default:
         break;
      case 2:
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, 0.0F, -1.0F);
         break;
      case 3:
         GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, -1.0F);
         break;
      case 4:
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-1.0F, 0.0F, 0.0F);
      }

      vertexBuffer.pos(pixel * startX, height, pixel * startZ).tex((double)sprite.getInterpolatedU(startX), (double)sprite.getInterpolatedV(startZ)).endVertex();
      vertexBuffer.pos(pixel * startX, height, pixel * (startZ + zz)).tex((double)sprite.getInterpolatedU(startX), (double)sprite.getInterpolatedV(startZ + zz)).endVertex();
      vertexBuffer.pos(pixel * (startX + xx), height, pixel * (startZ + zz)).tex((double)sprite.getInterpolatedU(startX + xx), (double)sprite.getInterpolatedV(startZ + zz)).endVertex();
      vertexBuffer.pos(pixel * (startX + xx), height, pixel * startZ).tex((double)sprite.getInterpolatedU(startX + xx), (double)sprite.getInterpolatedV(startZ)).endVertex();
   }
}
