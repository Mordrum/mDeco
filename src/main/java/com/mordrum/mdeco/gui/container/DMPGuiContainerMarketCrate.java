package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.network.DMPPacketUpdateMarketCrate;
import com.mordrum.mdeco.inventory.DMPContainerMarketCrate;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class DMPGuiContainerMarketCrate extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private static final ResourceLocation inventoryBackground = new ResourceLocation("mdeco", "textures/gui/container/marketCrate.png");
   private GuiTextField displayTextField1;
   private GuiTextField displayTextField2;
   private FontRenderer fontRendererObj;
   private DMPContainerMarketCrate container;

   public DMPGuiContainerMarketCrate(DMPContainerMarketCrate container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      super(container);
      this.container = container;
      this.xSize = 176;
      this.ySize = 208;
      this.world = worldIn;
      this.player = playerIn;
      this.pos = pos;
      this.fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
   }

   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.displayTextField1 = new GuiTextField(0, this.fontRendererObj, i + 56, j + 76, 80, 12);
      this.displayTextField1.setTextColor(-1);
      this.displayTextField1.setDisabledTextColour(-1);
      this.displayTextField1.setMaxStringLength(16);
      this.displayTextField1.setVisible(true);
      this.displayTextField1.setEnabled(true);
      this.displayTextField1.setEnableBackgroundDrawing(true);
      this.displayTextField1.setText(this.container.getDisplayText(1));
      this.displayTextField2 = new GuiTextField(0, this.fontRendererObj, i + 56, j + 96, 80, 12);
      this.displayTextField2.setTextColor(-1);
      this.displayTextField2.setDisabledTextColour(-1);
      this.displayTextField2.setMaxStringLength(16);
      this.displayTextField2.setVisible(true);
      this.displayTextField2.setEnabled(true);
      this.displayTextField2.setEnableBackgroundDrawing(true);
      this.displayTextField2.setText(this.container.getDisplayText(2));
   }

   public void drawGuiContainerForegroundLayer(int arg0, int arg1) {
      super.drawGuiContainerForegroundLayer(arg0, arg1);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.marketCrate"), 8, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, 115, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float i, int j, int k) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(inventoryBackground);
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.displayTextField1.drawTextBox();
      this.displayTextField2.drawTextBox();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      super.onGuiClosed();
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if(!this.displayTextField1.textboxKeyTyped(typedChar, keyCode) && !this.displayTextField2.textboxKeyTyped(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      } else {
         MDeco.networkManager.networkWrapper.sendToServer(new DMPPacketUpdateMarketCrate(this.pos, this.displayTextField1.getText(), this.displayTextField2.getText()));
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.displayTextField1.mouseClicked(mouseX, mouseY, mouseButton);
      this.displayTextField2.mouseClicked(mouseX, mouseY, mouseButton);
   }
}
