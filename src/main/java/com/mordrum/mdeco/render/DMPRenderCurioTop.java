package com.mordrum.mdeco.render;

import com.mordrum.mdeco.block.DMPBlockCurioTop;
import com.mordrum.mdeco.tileentity.DMPTileEntityCurioTop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class DMPRenderCurioTop extends TileEntitySpecialRenderer {
   private RenderItem renderItem;
   private RenderManager renderManager;
   public int jLast = 0;

   public DMPRenderCurioTop(RenderManager rm, RenderItem ri) {
      this.renderManager = rm;
      this.renderItem = ri;
   }

   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
      this.render((DMPTileEntityCurioTop)te, x, y, z, p_180535_8_, p_180535_9_);
   }

   public void render(DMPTileEntityCurioTop te, double x, double y, double z, float par6, int par7) {
      BlockPos blockPos = te.getPos();
      IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
      if(myBlockState.getBlock() instanceof DMPBlockCurioTop) {
         EnumFacing facing = (EnumFacing)myBlockState.getValue(DMPBlockCurioTop.FACING);

         for(int i = 0; i < 8; ++i) {
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
               GL11.glTranslatef(0.0F, i < 4?-0.2F:0.825F, 0.0F);
               GL11.glScalef(0.9F, 0.9F, 0.9F);
               if(i != 0 && i != 4) {
                  if(i != 1 && i != 5) {
                     if(i != 2 && i != 6) {
                        if(i == 3 || i == 7) {
                           this.renderSouthItem(customItem, facing);
                        }
                     } else {
                        this.renderEastItem(customItem, facing);
                     }
                  } else {
                     this.renderWestItem(customItem, facing);
                  }
               } else {
                  this.renderNorthItem(customItem, facing);
               }

               GL11.glPopMatrix();
               GL11.glEnable(3008);
               GL11.glPopMatrix();
            }
         }

      }
   }

   private void renderNorthItem(EntityItem customItem, EnumFacing facing) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.5F, 0.0F, 1.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(1.0F, 0.0F, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderSouthItem(EntityItem customItem, EnumFacing facing) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.5F, 0.0F, 1.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(1.0F, 0.0F, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderWestItem(EntityItem customItem, EnumFacing facing) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(1.0F, 0.0F, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.5F, 0.0F, 1.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }

   private void renderEastItem(EntityItem customItem, EnumFacing facing) {
      if(facing == EnumFacing.NORTH) {
         GL11.glTranslatef(1.0F, 0.0F, 0.5F);
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.SOUTH) {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.WEST) {
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      } else if(facing == EnumFacing.EAST) {
         GL11.glTranslatef(0.5F, 0.0F, 1.0F);
      }

      this.renderManager.doRenderEntity(customItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
   }
}
