package com.mordrum.mdeco.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DMPGuiCore {
   public static final ResourceLocation imageLogo = new ResourceLocation("mdeco:textures/gui/logoDMP.png");
   public static final ResourceLocation ledGRNOff = new ResourceLocation("mdeco:textures/gui/ledGRN0.png");
   public static final ResourceLocation ledGRNOn = new ResourceLocation("mdeco:textures/gui/ledGRN1.png");
   public static final int BORDER = 6;
   public static final int BUTTON_HEIGHT = 20;
   public static final int BUTTON_WIDTH_LARGE = 180;
   public static final int BUTTON_WIDTH_MED = 150;
   public static final int BUTTON_WIDTH_SMALL = 88;
   public static final int BUTTON_SPACING = 4;
   public static final int LED_HEIGHT = 20;
   public static final int LED_SPACING = 0;
   public static final int LED_WIDTH = 6;
   public static final int LOGO_HEIGHT = 16;
   public static final int LOGO_WIDTH = 170;
   public static final int MIN_ICON_DRAW_SIZE = 16;
   public static final int START_Y = 36;
   public static final int TEXT_COLOR_BLUE = 2121952;
   public static final int TEXT_COLOR_DKBLU = 80;
   public static final int TEXT_COLOR_GRAY = 6316128;
   public static final int TEXT_COLOR_MEDBLU = 8421600;
   public static final int TEXT_COLOR_LTBLU = 9482495;
   public static final int TEXT_COLOR_YELLOW = 16777088;

   public static void drawLED(int x, int y, boolean enabled, boolean indicatorOn) {
      ResourceLocation texture = enabled & indicatorOn?ledGRNOn:ledGRNOff;
      drawTexture(texture, x, y, 6, 20);
   }

   public static void drawLEDBar(int x, int y, int elements, int lightedElements) {
      if(elements >= 1 && elements <= 20 && lightedElements >= 0 && lightedElements <= elements) {
         for(int index = 1; index <= elements; ++index) {
            drawLED(x + 6 * (index - 1), y, index <= lightedElements, true);
         }

      }
   }

   public static void drawBlockTexture(String unlocalizedName, int xPos, int yPos, int size) {
      if(!unlocalizedName.isEmpty() && size >= 16) {
         ResourceLocation itemTextureFile = new ResourceLocation("mdeco:textures/blocks/" + unlocalizedName + ".png");
         if(itemTextureFile != null) {
            drawTexture(itemTextureFile, xPos, yPos, size, size);
         }
      }

   }

   public static void drawTexture(ResourceLocation texture, int x, int y, int width, int height) {
      if(texture != null) {
         Tessellator tessellator = Tessellator.getInstance();
         VertexBuffer worldrenderer = tessellator.getBuffer();
         Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         float zLevel = 1.0F;
         GlStateManager.enableBlend();
         worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldrenderer.pos((double)x, (double)(y + height), (double)zLevel).tex(0.0D, 1.0D).endVertex();
         worldrenderer.pos((double)(x + width), (double)(y + height), (double)zLevel).tex(1.0D, 1.0D).endVertex();
         worldrenderer.pos((double)(x + width), (double)y, (double)zLevel).tex(1.0D, 0.0D).endVertex();
         worldrenderer.pos((double)x, (double)y, (double)zLevel).tex(0.0D, 0.0D).endVertex();
         tessellator.draw();
         GlStateManager.disableBlend();
      }
   }
}
