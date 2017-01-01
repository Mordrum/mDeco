package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.inventory.DMPContainerWorkbenchSaw;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class DMPGuiContainerWorkbenchSaw extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private IInventory tileWorkbenchSaw;
   private static final ResourceLocation guiBackground = new ResourceLocation("mdeco", "textures/gui/container/workbenchSaw.png");

   public DMPGuiContainerWorkbenchSaw(DMPContainerWorkbenchSaw container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      super(container);
      this.xSize = 176;
      this.ySize = 138;
      this.world = worldIn;
      this.player = playerIn;
      this.pos = pos;
      this.tileWorkbenchSaw = (IInventory)worldIn.getTileEntity(pos);
   }

   public void initGui() {
      super.initGui();
   }

   public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      super.drawGuiContainerForegroundLayer(mouseX, mouseY);
      String text = I18n.translateToLocal("container.workbenchSaw");
      int stringWidth = this.fontRendererObj.getStringWidth(text);
      int posX = this.xSize / 2 - stringWidth / 2;
      this.fontRendererObj.drawString(text, posX, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, 44, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(guiBackground);
      int startX = (this.width - this.xSize) / 2;
      int startY = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(startX, startY, 0, 0, this.xSize, this.ySize);
      if(this.tileWorkbenchSaw.getField(0) > 0) {
         int i1 = MDeco.settings.workbenchSawCutTime - this.tileWorkbenchSaw.getField(0);
         int i2 = i1 * 24 / MDeco.settings.workbenchSawCutTime;
         this.drawTexturedModalRect(startX + 72, startY + 20, 176, 0, i2, 16);
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
   }
}
