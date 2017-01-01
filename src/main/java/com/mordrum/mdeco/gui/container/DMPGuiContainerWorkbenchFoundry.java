package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.inventory.DMPContainerWorkbenchFoundry;
import java.io.IOException;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class DMPGuiContainerWorkbenchFoundry extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private static final ResourceLocation guiBackground = new ResourceLocation("mdeco", "textures/gui/container/workbenchFoundry.png");

   public DMPGuiContainerWorkbenchFoundry(DMPContainerWorkbenchFoundry container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      this(playerIn, worldIn, pos);
   }

   public DMPGuiContainerWorkbenchFoundry(EntityPlayer playerIn, World worldIn, BlockPos pos) {
      super(new DMPContainerWorkbenchFoundry(worldIn, pos, playerIn));
      this.xSize = 176;
      this.ySize = 168;
      this.world = worldIn;
      this.player = playerIn;
      this.pos = pos;
   }

   public void initGui() {
      super.initGui();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
   }

   public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      super.drawGuiContainerForegroundLayer(mouseX, mouseY);
      String text = I18n.translateToLocal("container.workbenchFoundry");
      int stringWidth = this.fontRendererObj.getStringWidth(text);
      int posX = this.xSize / 2 - stringWidth / 2;
      this.fontRendererObj.drawString(text, posX, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(guiBackground);
      int startX = (this.width - this.xSize) / 2;
      int startY = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(startX, startY, 0, 0, this.xSize, this.ySize);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
   }
}
