package com.mordrum.mdeco.gui.container;

import com.mordrum.mdeco.inventory.DMPContainerCabinetWall;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class DMPGuiContainerCabinetWall extends GuiContainer {
   public World world;
   public EntityPlayer player;
   public BlockPos pos;
   public int list;
   private static final ResourceLocation inventoryBackground = new ResourceLocation("mdeco", "textures/gui/container/cabinetWall.png");

   public DMPGuiContainerCabinetWall(DMPContainerCabinetWall container, World worldIn, EntityPlayer playerIn, BlockPos pos) {
      super(container);
      this.xSize = 176;
      this.ySize = 186;
      this.world = worldIn;
      this.player = playerIn;
      this.pos = pos;
   }

   public void initGui() {
      super.initGui();
   }

   public void drawGuiContainerForegroundLayer(int arg0, int arg1) {
      super.drawGuiContainerForegroundLayer(arg0, arg1);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.cabinetWall"), 8, 6, 4210752);
      this.fontRendererObj.drawString(I18n.translateToLocal("container.inventory"), 8, 92, 4210752);
   }

   public void drawGuiContainerBackgroundLayer(float i, int j, int k) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(inventoryBackground);
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
   }
}
