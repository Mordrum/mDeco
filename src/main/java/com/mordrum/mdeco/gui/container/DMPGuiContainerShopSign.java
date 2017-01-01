package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.network.DMPPacketUpdateShopSign;
import com.mordrum.mdeco.inventory.DMPContainerShopSign;

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
public class DMPGuiContainerShopSign extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private static final ResourceLocation inventoryBackground = new ResourceLocation("mdeco", "textures/gui/container/shopSign.png");
   private GuiTextField signTextField1;
   private GuiTextField signTextField2;
   private FontRenderer fontRendererObj;
   private DMPContainerShopSign container;

   public DMPGuiContainerShopSign(DMPContainerShopSign container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      super(container);
      this.container = container;
      this.xSize = 176;
      this.ySize = 172;
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
      this.signTextField1 = new GuiTextField(0, this.fontRendererObj, i + 56, j + 40, 80, 12);
      this.signTextField1.setTextColor(-1);
      this.signTextField1.setDisabledTextColour(-1);
      this.signTextField1.setMaxStringLength(16);
      this.signTextField1.setVisible(true);
      this.signTextField1.setEnabled(true);
      this.signTextField1.setEnableBackgroundDrawing(true);
      this.signTextField1.setText(this.container.getSignText(1));
      this.signTextField2 = new GuiTextField(0, this.fontRendererObj, i + 56, j + 60, 80, 12);
      this.signTextField2.setTextColor(-1);
      this.signTextField2.setDisabledTextColour(-1);
      this.signTextField2.setMaxStringLength(16);
      this.signTextField2.setVisible(true);
      this.signTextField2.setEnabled(true);
      this.signTextField2.setEnableBackgroundDrawing(true);
      this.signTextField2.setText(this.container.getSignText(2));
   }

   public void drawGuiContainerForegroundLayer(int arg0, int arg1) {
      super.drawGuiContainerForegroundLayer(arg0, arg1);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.shopSign"), 8, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, 79, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float i, int j, int k) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(inventoryBackground);
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.signTextField1.drawTextBox();
      this.signTextField2.drawTextBox();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      super.onGuiClosed();
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if(!this.signTextField1.textboxKeyTyped(typedChar, keyCode) && !this.signTextField2.textboxKeyTyped(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      } else {
         MDeco.networkManager.networkWrapper.sendToServer(new DMPPacketUpdateShopSign(this.pos, this.signTextField1.getText(), this.signTextField2.getText()));
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.signTextField1.mouseClicked(mouseX, mouseY, mouseButton);
      this.signTextField2.mouseClicked(mouseX, mouseY, mouseButton);
   }
}
