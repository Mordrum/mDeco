package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockMarketStand;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderMarketStand extends TileEntitySpecialRenderer {
	private RenderManager renderManager;
   public int jLast = 0;

   public DMPRenderMarketStand(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityMarketStand)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   public void render(DMPTileEntityMarketStand te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockMarketStand) {
         int facing = myBlockState.getValue(DMPBlockMarketStand.FACING).getHorizontalIndex();
         float posHeight = 0.0F;
         float pixel = 0.0625F;

         for(int i = 0; i < 18; ++i) {
            if(te.getStackInSlot(i) != null) {
               GL11.glPushMatrix();
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               GL11.glTranslated(x, y, z);
               GL11.glPushMatrix();
               GL11.glScalef(0.5F, 0.5F, 0.5F);
               GL11.glTranslatef(1.5F, 0.15F, 0.5F);
               GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(-0.5F, 0.0F, 0.5F);
               GL11.glTranslated(0.0D, 0.0D, 0.0D);
               EntityItem customItem = new EntityItem(te.getWorld(), (double)te.getPos().getX(), (double)te.getPos().getY(), (double)te.getPos().getZ(), te.getStackInSlot(i));
               customItem.hoverStart = 0.0F;
               customItem.posX = (double)te.getPos().getX();
               customItem.posY = (double)(te.getPos().getY() + 2);
               customItem.posZ = (double)te.getPos().getZ();
               GL11.glScalef(0.9F, 0.9F, 0.9F);
               if(i >= 0 && i < 6) {
                  posHeight = 1.95F;
               } else if(i >= 6 && i < 12) {
                  posHeight = 1.65F;
               } else {
                  posHeight = 1.35F;
               }

               if(facing == 0) {
                  if(i == 0) {
                     GL11.glTranslatef(0.77F, posHeight, 0.75F);
                  }

                  if(i == 1) {
                     GL11.glTranslatef(0.52F, posHeight, 0.75F - pixel * 2.5F);
                  }

                  if(i == 2) {
                     GL11.glTranslatef(0.27F, posHeight, 0.75F);
                  }

                  if(i == 3) {
                     GL11.glTranslatef(-0.27F, posHeight, 0.75F);
                  }

                  if(i == 4) {
                     GL11.glTranslatef(-0.52F, posHeight, 0.75F - pixel * 2.5F);
                  }

                  if(i == 5) {
                     GL11.glTranslatef(-0.77F, posHeight, 0.75F);
                  }

                  if(i == 6) {
                     GL11.glTranslatef(0.77F, posHeight, 0.05F);
                  }

                  if(i == 7) {
                     GL11.glTranslatef(0.52F, posHeight, 0.05F - pixel * 2.5F);
                  }

                  if(i == 8) {
                     GL11.glTranslatef(0.27F, posHeight, 0.05F);
                  }

                  if(i == 9) {
                     GL11.glTranslatef(-0.27F, posHeight, 0.05F);
                  }

                  if(i == 10) {
                     GL11.glTranslatef(-0.52F, posHeight, 0.05F - pixel * 2.5F);
                  }

                  if(i == 11) {
                     GL11.glTranslatef(-0.77F, posHeight, 0.05F);
                  }

                  if(i == 12) {
                     GL11.glTranslatef(0.77F, posHeight, -0.65F);
                  }

                  if(i == 13) {
                     GL11.glTranslatef(0.52F, posHeight, -0.65F - pixel * 2.5F);
                  }

                  if(i == 14) {
                     GL11.glTranslatef(0.27F, posHeight, -0.65F);
                  }

                  if(i == 15) {
                     GL11.glTranslatef(-0.27F, posHeight, -0.65F);
                  }

                  if(i == 16) {
                     GL11.glTranslatef(-0.52F, posHeight, -0.65F - pixel * 2.5F);
                  }

                  if(i == 17) {
                     GL11.glTranslatef(-0.77F, posHeight, -0.65F);
                  }

                  GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               } else if(facing == 1) {
                  if(i == 0) {
                     GL11.glTranslatef(-0.75F, posHeight, 0.77F);
                  }

                  if(i == 1) {
                     GL11.glTranslatef(-0.75F + pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 2) {
                     GL11.glTranslatef(-0.75F, posHeight, 0.27F);
                  }

                  if(i == 3) {
                     GL11.glTranslatef(-0.75F, posHeight, -0.27F);
                  }

                  if(i == 4) {
                     GL11.glTranslatef(-0.75F + pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 5) {
                     GL11.glTranslatef(-0.75F, posHeight, -0.77F);
                  }

                  if(i == 6) {
                     GL11.glTranslatef(-0.05F, posHeight, 0.77F);
                  }

                  if(i == 7) {
                     GL11.glTranslatef(-0.05F + pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 8) {
                     GL11.glTranslatef(-0.05F, posHeight, 0.27F);
                  }

                  if(i == 9) {
                     GL11.glTranslatef(-0.05F, posHeight, -0.27F);
                  }

                  if(i == 10) {
                     GL11.glTranslatef(-0.05F + pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 11) {
                     GL11.glTranslatef(-0.05F, posHeight, -0.77F);
                  }

                  if(i == 12) {
                     GL11.glTranslatef(0.65F, posHeight, 0.77F);
                  }

                  if(i == 13) {
                     GL11.glTranslatef(0.65F + pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 14) {
                     GL11.glTranslatef(0.65F, posHeight, 0.27F);
                  }

                  if(i == 15) {
                     GL11.glTranslatef(0.65F, posHeight, -0.27F);
                  }

                  if(i == 16) {
                     GL11.glTranslatef(0.65F + pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 17) {
                     GL11.glTranslatef(0.65F, posHeight, -0.77F);
                  }

                  GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
               }

               if(facing == 2) {
                  if(i == 0) {
                     GL11.glTranslatef(-0.77F, posHeight, -0.75F);
                  }

                  if(i == 1) {
                     GL11.glTranslatef(-0.52F, posHeight, -0.75F + pixel * 2.5F);
                  }

                  if(i == 2) {
                     GL11.glTranslatef(-0.27F, posHeight, -0.75F);
                  }

                  if(i == 3) {
                     GL11.glTranslatef(0.27F, posHeight, -0.75F);
                  }

                  if(i == 4) {
                     GL11.glTranslatef(0.52F, posHeight, -0.75F + pixel * 2.5F);
                  }

                  if(i == 5) {
                     GL11.glTranslatef(0.77F, posHeight, -0.75F);
                  }

                  if(i == 6) {
                     GL11.glTranslatef(-0.77F, posHeight, -0.05F);
                  }

                  if(i == 7) {
                     GL11.glTranslatef(-0.52F, posHeight, -0.05F + pixel * 2.5F);
                  }

                  if(i == 8) {
                     GL11.glTranslatef(-0.27F, posHeight, -0.05F);
                  }

                  if(i == 9) {
                     GL11.glTranslatef(0.27F, posHeight, -0.05F);
                  }

                  if(i == 10) {
                     GL11.glTranslatef(0.52F, posHeight, -0.05F + pixel * 2.5F);
                  }

                  if(i == 11) {
                     GL11.glTranslatef(0.77F, posHeight, -0.05F);
                  }

                  if(i == 12) {
                     GL11.glTranslatef(-0.77F, posHeight, 0.65F);
                  }

                  if(i == 13) {
                     GL11.glTranslatef(-0.52F, posHeight, 0.65F + pixel * 2.5F);
                  }

                  if(i == 14) {
                     GL11.glTranslatef(-0.27F, posHeight, 0.65F);
                  }

                  if(i == 15) {
                     GL11.glTranslatef(0.27F, posHeight, 0.65F);
                  }

                  if(i == 16) {
                     GL11.glTranslatef(0.52F, posHeight, 0.65F + pixel * 2.5F);
                  }

                  if(i == 17) {
                     GL11.glTranslatef(0.77F, posHeight, 0.65F);
                  }
               } else if(facing == 3) {
                  if(i == 0) {
                     GL11.glTranslatef(0.75F, posHeight, -0.77F);
                  }

                  if(i == 1) {
                     GL11.glTranslatef(0.75F - pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 2) {
                     GL11.glTranslatef(0.75F, posHeight, -0.27F);
                  }

                  if(i == 3) {
                     GL11.glTranslatef(0.75F, posHeight, 0.27F);
                  }

                  if(i == 4) {
                     GL11.glTranslatef(0.75F - pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 5) {
                     GL11.glTranslatef(0.75F, posHeight, 0.77F);
                  }

                  if(i == 6) {
                     GL11.glTranslatef(0.05F, posHeight, -0.77F);
                  }

                  if(i == 7) {
                     GL11.glTranslatef(0.05F - pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 8) {
                     GL11.glTranslatef(0.05F, posHeight, -0.27F);
                  }

                  if(i == 9) {
                     GL11.glTranslatef(0.05F, posHeight, 0.27F);
                  }

                  if(i == 10) {
                     GL11.glTranslatef(0.05F - pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 11) {
                     GL11.glTranslatef(0.05F, posHeight, 0.77F);
                  }

                  if(i == 12) {
                     GL11.glTranslatef(-0.65F, posHeight, -0.77F);
                  }

                  if(i == 13) {
                     GL11.glTranslatef(-0.65F - pixel * 2.5F, posHeight, -0.52F);
                  }

                  if(i == 14) {
                     GL11.glTranslatef(-0.65F, posHeight, -0.27F);
                  }

                  if(i == 15) {
                     GL11.glTranslatef(-0.65F, posHeight, 0.27F);
                  }

                  if(i == 16) {
                     GL11.glTranslatef(-0.65F - pixel * 2.5F, posHeight, 0.52F);
                  }

                  if(i == 17) {
                     GL11.glTranslatef(-0.65F, posHeight, 0.77F);
                  }

                  GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
               }

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

      }
   }
}
