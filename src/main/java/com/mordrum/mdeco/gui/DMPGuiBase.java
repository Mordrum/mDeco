package com.mordrum.mdeco.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class DMPGuiBase extends GuiScreen {
   protected GuiButton buttonDefault;
   protected GuiButton buttonDone;
   protected String name;
   protected GuiScreen parentGuiScreen;
   private int buttonLayout;

   public DMPGuiBase(GuiScreen parentGuiScreen, String screenName, int buttonLayout) {
      this.parentGuiScreen = parentGuiScreen;
      this.name = I18n.translateToLocal(screenName);
      this.buttonLayout = buttonLayout >= -1 && buttonLayout <= 2?buttonLayout:0;
   }

   public void initGui() {
      int yPos = this.height - 27;
      if(this.buttonLayout >= 0) {
         int xPos;
         if(this.buttonLayout == 0) {
            xPos = this.width / 2 - 90;
            this.buttonDone = new GuiButton(2, xPos, this.height - 27, 180, 20, I18n.translateToLocal("gui.done"));
            this.buttonList.add(this.buttonDone);
         } else if(this.buttonLayout == 1) {
            xPos = this.width / 2 - 121;
            this.buttonDefault = new GuiButton(1, xPos, yPos, 88, 20, I18n.translateToLocal("createWorld.customize.custom.defaults"));
            xPos += 92;
            this.buttonDone = new GuiButton(2, xPos, yPos, 150, 20, I18n.translateToLocal("gui.done"));
            this.buttonList.add(this.buttonDefault);
            this.buttonList.add(this.buttonDone);
         } else if(this.buttonLayout == 2) {
            xPos = this.width / 2 - 121;
            this.buttonDefault = new GuiButton(1, xPos, yPos, 88, 20, I18n.translateToLocal("gui.screen.text.cancel"));
            xPos += 92;
            this.buttonDone = new GuiButton(2, xPos, yPos, 150, 20, I18n.translateToLocal("gui.screen.text.proceed"));
            this.buttonList.add(this.buttonDefault);
            this.buttonList.add(this.buttonDone);
         }
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);
      this.drawLogoImage();
      this.drawTitleText();
   }

   protected void actionPerformed(GuiButton button) {
      if(button.enabled) {
         if(this.buttonLayout == 2) {
            this.mc.displayGuiScreen(this.parentGuiScreen);
            return;
         }

         switch(button.id) {
         case 1:
            this.mc.displayGuiScreen(this);
            return;
         case 2:
            this.mc.displayGuiScreen(this.parentGuiScreen);
         }
      }

   }

   protected void drawLogoImage() {
      if(DMPGuiCore.imageLogo != null) {
         int x = (this.width - 170) / 2;
         byte y = 3;
         DMPGuiCore.drawTexture(DMPGuiCore.imageLogo, x, y, 170, 16);
      }

   }

   protected void drawTitleText() {
      this.drawCenteredString(this.fontRendererObj, this.name, this.width / 2, 20, 9482495);
   }

   protected FontRenderer getFontRenderer() {
      return this.fontRendererObj;
   }

   public void listItemDoubleClicked(DMPGuiListbox list, int index) {
   }

   public void listItemSelected(DMPGuiListbox list, int index) {
   }

   public void onSliderUpdate(DMPGuiSlider slider, int value) {
   }
}
