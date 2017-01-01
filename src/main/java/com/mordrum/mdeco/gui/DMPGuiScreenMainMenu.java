package com.mordrum.mdeco.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DMPGuiScreenMainMenu extends DMPGuiBase {

	public DMPGuiScreenMainMenu(GuiScreen parent) {
      super(parent, "gui.screen.mainMenu", 0);
   }

   public void initGui() {
      super.initGui();
      int xPos = this.width / 2 - 90;
      byte yPos = 36;
	   DMPGuiButton buttonTools = new DMPGuiButton(3, DMPGuiButtonMode.normal, xPos, yPos, 180, 20,
			   I18n.translateToLocal("gui.screen.tools") + "...");
      this.buttonList.add(buttonTools);
   }

   protected void actionPerformed(GuiButton button) {
      if(button.enabled) {
         switch(button.id) {
         case 3:
            this.mc.displayGuiScreen(new DMPGuiScreenTools(this));
            return;
         }
      }

      super.actionPerformed(button);
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      super.drawScreen(par1, par2, par3);
   }
}
