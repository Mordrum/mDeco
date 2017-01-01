package com.mordrum.mdeco.gui;

import com.mordrum.mdeco.MDeco;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DMPGuiScreenTools extends DMPGuiBase {
   private static final ResourceLocation textureWorkbenchSaw = new ResourceLocation("mdeco:textures/blocks/workbenchSaw.png");
   private static final ResourceLocation textureWorkbenchFoundry = new ResourceLocation("mdeco:textures/blocks/workbenchFoundry3.png");
   private DMPGuiSlider workbenchSawCutTime;
   private DMPGuiButton workbenchFoundryParticles;

   public DMPGuiScreenTools(GuiScreen parent) {
      super(parent, "gui.screen.tools", 1);
   }

   public void initGui() {
      super.initGui();
      int startX = this.width / 2 - 104;
      startX += 24;
      byte yPos = 36;
      this.workbenchSawCutTime = new DMPGuiSlider(this, 4, startX, yPos, 180, 20, "option.workbench.saw.cutTime", 50, 100, 200, 1);
      this.workbenchSawCutTime.setSliderMode(DMPGuiSliderMode.normal);
      int yPos1 = yPos + 24;
      this.workbenchFoundryParticles = new DMPGuiButton(5, DMPGuiButtonMode.toggle_green, startX, yPos1, 180, 20, I18n.translateToLocal("option.workbench.foundry.particles"));
      this.updateButtonStatesFromSettings();
      this.buttonList.add(this.workbenchSawCutTime);
      this.buttonList.add(this.workbenchFoundryParticles);
   }

   protected void actionPerformed(GuiButton button) {
      if(button.enabled) {
         switch(button.id) {
         case 1:
            MDeco.settings.resetTools();
            this.updateButtonStatesFromSettings();
            break;
         case 2:
            this.updateSettingsFromButtonStates();
            MDeco.settings.saveOptions(false);
         }
      }

      super.actionPerformed(button);
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      super.drawScreen(par1, par2, par3);
      int startX = this.width / 2 - 104;
      byte yPos = 36;
      DMPGuiCore.drawTexture(textureWorkbenchSaw, startX, yPos, 20, 20);
      int yPos1 = yPos + 24;
      DMPGuiCore.drawTexture(textureWorkbenchFoundry, startX, yPos1, 20, 20);
   }

   private void updateButtonStatesFromSettings() {
      this.workbenchSawCutTime.setIntValue(MDeco.settings.workbenchSawCutTime);
      this.workbenchSawCutTime.updateDisplayString();
      this.workbenchFoundryParticles.setButtonState(MDeco.settings.workbenchFoundryParticles);
   }

   private void updateSettingsFromButtonStates() {
      MDeco.settings.workbenchSawCutTime = this.workbenchSawCutTime.getIntValue();
      MDeco.settings.workbenchFoundryParticles = this.workbenchFoundryParticles.getButtonState();
   }
}
