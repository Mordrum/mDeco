package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.inventory.DMPContainerPoleSign;
import com.mordrum.mdeco.network.DMPPacketUpdatePoleSign;
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
public class DMPGuiContainerPoleSign extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private static final ResourceLocation inventoryBackground = new ResourceLocation("mdeco", "textures/gui/container/poleSign.png");
   private GuiTextField signTextField;
   private FontRenderer fontRendererObj;
   private DMPContainerPoleSign container;

   public DMPGuiContainerPoleSign(DMPContainerPoleSign container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      super(container);
      this.container = container;
      this.xSize = 176;
      this.ySize = 132;
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
      this.signTextField = new GuiTextField(0, this.fontRendererObj, i + 38, j + 20, 120, 12);
      this.signTextField.setTextColor(-1);
      this.signTextField.setDisabledTextColour(-1);
      this.signTextField.setMaxStringLength(24);
      this.signTextField.setVisible(true);
      this.signTextField.setEnabled(true);
      this.signTextField.setEnableBackgroundDrawing(true);
      this.signTextField.setText(this.container.getSignText());
   }

   public void drawGuiContainerForegroundLayer(int arg0, int arg1) {
      super.drawGuiContainerForegroundLayer(arg0, arg1);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.poleSign"), 8, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, 39, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float i, int j, int k) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(inventoryBackground);
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.signTextField.drawTextBox();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      super.onGuiClosed();
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if(this.signTextField.textboxKeyTyped(typedChar, keyCode)) {
         MDeco.networkManager.networkWrapper.sendToServer(new DMPPacketUpdatePoleSign(this.pos, this.signTextField.getText()));
      } else {
         super.keyTyped(typedChar, keyCode);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.signTextField.mouseClicked(mouseX, mouseY, mouseButton);
   }
}
