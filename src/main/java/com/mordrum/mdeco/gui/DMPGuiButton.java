package com.mordrum.mdeco.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DMPGuiButton extends GuiButton {
   private DMPGuiButtonMode buttonMode;
   private boolean buttonState;
   private boolean selected;
   private boolean displayOnOff;

   public DMPGuiButton(int id, DMPGuiButtonMode mode, int xPosition, int yPosition, int width, int height, String label) {
      super(id, xPosition, yPosition, label);
      this.buttonMode = mode;
      this.buttonState = false;
      this.selected = false;
      this.displayOnOff = true;
      this.width = width;
      this.height = height;
   }

   public void setDisplayOnOff(boolean state) {
      this.displayOnOff = state;
   }

   public void drawButton(Minecraft mc, int xPos, int yPos) {
      if(this.visible) {
         int buttonStartXPos = this.xPosition;
         int buttonWidth = this.width;
         if(this.buttonMode != DMPGuiButtonMode.normal && this.buttonMode != DMPGuiButtonMode.toggle_noLed) {
            DMPGuiCore.drawLED(this.xPosition, this.yPosition, this.enabled, this.buttonState);
            buttonStartXPos += 6;
            buttonWidth -= 6;
         }

         FontRenderer fontrenderer = mc.fontRendererObj;
         mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = xPos >= buttonStartXPos && yPos >= this.yPosition && xPos < buttonStartXPos + buttonWidth && yPos < this.yPosition + this.height;
         int k = this.getHoverState(this.hovered);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(770, 771);
         if(this.enabled) {
            if(this.selected) {
               k = 1;
               GL11.glColor4f(0.5F, 1.0F, 0.5F, 1.0F);
            } else {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
         } else {
            GL11.glColor4f(0.5F, 0.55F, 0.5F, 1.0F);
         }

         this.drawTexturedModalRect(buttonStartXPos, this.yPosition, 0, 46 + k * 20, buttonWidth / 2, this.height);
         this.drawTexturedModalRect(buttonStartXPos + buttonWidth / 2, this.yPosition, 200 - buttonWidth / 2, 46 + k * 20, buttonWidth / 2, this.height);
         this.mouseDragged(mc, xPos, yPos);
         int textColor = 9482495;
         if(this.selected) {
            textColor = 9482495;
         } else if(!this.enabled) {
            textColor = 80;
         } else if(this.hovered) {
            textColor = 16777088;
         }

         this.drawCenteredString(fontrenderer, this.getDisplayString(), buttonStartXPos + buttonWidth / 2, this.yPosition + (this.height - 8) / 2, textColor);
      }

   }

   public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
      if(!this.selected && super.mousePressed(mc, xPos, yPos)) {
         if(this.buttonMode != DMPGuiButtonMode.normal) {
            if(xPos < this.xPosition + 6) {
               return false;
            }

            this.buttonState = !this.buttonState;
         }

         return true;
      } else {
         return false;
      }
   }

   private String getDisplayString() {
      return this.displayOnOff && this.buttonMode != DMPGuiButtonMode.normal?this.displayString + ": " + (this.buttonState?I18n.translateToLocal("options.on"):I18n.translateToLocal("options.off")):this.displayString;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }

   public void setButtonMode(DMPGuiButtonMode mode) {
      this.buttonMode = mode;
   }

   public boolean getButtonState() {
      return this.buttonState;
   }

   public void setButtonState(boolean state) {
      this.buttonState = state;
   }
}
