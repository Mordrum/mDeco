package com.mordrum.mdeco.gui;

import com.google.common.collect.Lists;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class DMPGuiListbox extends GuiListExtended {
   private static final ResourceLocation listBackground = new ResourceLocation("plantmegapack:textures/gui/bookBackground.png");
   private IDMPListBoxOwner parent;
   private List listEntries = Lists.newArrayList();
   public boolean visible;
   private boolean drawCustom;

   public DMPGuiListbox(IDMPListBoxOwner parent, int xPos, int yPos, int width, int height, int slotHeight) {
      super(Minecraft.getMinecraft(), width, parent.getHeight(), yPos, height, slotHeight);
      this.parent = parent;
      this.visible = true;
      this.drawCustom = false;
      this.left = xPos;
      this.right = xPos + width;
      this.bottom = yPos + height;
      this.setHasListHeader(false, 0);
      this.showSelectionBox = true;
      this.registerScrollButtons(7, 8);
   }

   public void addListboxEntry(String name, String localizedName) {
      this.listEntries.add(new DMPGuiListboxEntry(name, localizedName, this));
   }

   public boolean getCustomDrawMode() {
      return this.drawCustom;
   }

   public void setCustomDrawMode(boolean customDraw) {
      this.drawCustom = customDraw;
   }

   public void scrollToAndSelectEntry(int slotID) {
      this.selectListItem(slotID);
      this.amountScrolled = (float)(slotID * this.slotHeight + this.height / this.slotHeight - 4 - this.slotHeight);
      this.bindAmountScrolled();
   }

   public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
      if(this.visible) {
         this.drawScreenCustom(mouseXIn, mouseYIn, partialTicks);
      }

   }

   private void drawScreenCustom(int mouseXIn, int mouseYIn, float partialTicks) {
      if(this.visible) {
         this.mouseX = mouseXIn;
         this.mouseY = mouseYIn;
         this.drawBackground();
         int i = this.getScrollBarX();
         int j = i + 6;
         this.bindAmountScrolled();
         GlStateManager.disableLighting();
         GlStateManager.disableFog();
         Tessellator tessellator = Tessellator.getInstance();
         VertexBuffer worldrenderer = tessellator.getBuffer();
         this.drawContainerBackground(tessellator);
         int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
         int l = this.top + 4 - (int)this.amountScrolled;
         this.drawSelectionBox(k, l, mouseXIn, mouseYIn);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.disableAlpha();
         GlStateManager.shadeModel(7425);
         GlStateManager.disableTexture2D();
         int j1 = this.getMaxScroll();
         if(j1 > 0) {
            int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
            k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
            int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;
            if(l1 < this.top) {
               l1 = this.top;
            }

            int color = this.drawCustom?9729114:0;
            int r = color >> 16 & 255;
            int g = color >> 8 & 255;
            int b = color & 255;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)i, (double)this.bottom, 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j, (double)this.bottom, 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j, (double)this.top, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)i, (double)this.top, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            tessellator.draw();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)i, (double)(l1 + k1), 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j, (double)(l1 + k1), 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j, (double)l1, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            tessellator.draw();
            color = this.drawCustom?13746081:6316128;
            r = color >> 16 & 255;
            g = color >> 8 & 255;
            b = color & 255;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldrenderer.pos((double)i, (double)(l1 + k1 - 1), 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)(j - 1), (double)(l1 + k1 - 1), 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)(j - 1), (double)l1, 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)i, (double)l1, 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            tessellator.draw();
         }

         this.renderDecorations(mouseXIn, mouseYIn);
         GlStateManager.enableTexture2D();
         GlStateManager.shadeModel(7424);
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
      }

   }

   protected void drawContainerBackground(Tessellator tessellator) {
      VertexBuffer worldrenderer = tessellator.getBuffer();
      this.mc.getTextureManager().bindTexture(this.drawCustom?listBackground:Gui.OPTIONS_BACKGROUND);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f = 32.0F;
      int color = this.drawCustom?16777215:0;
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldrenderer.pos((double)this.left, (double)this.bottom, 0.0D).tex((double)((float)this.left / f), (double)((float)(this.bottom + (int)this.amountScrolled) / f)).color(r, g, b, 255).endVertex();
      worldrenderer.pos((double)this.right, (double)this.bottom, 0.0D).tex((double)((float)this.right / f), (double)((float)(this.bottom + (int)this.amountScrolled) / f)).color(r, g, b, 255).endVertex();
      worldrenderer.pos((double)this.right, (double)this.top, 0.0D).tex((double)((float)this.right / f), (double)((float)(this.top + (int)this.amountScrolled) / f)).color(r, g, b, 255).endVertex();
      worldrenderer.pos((double)this.left, (double)this.top, 0.0D).tex((double)((float)this.left / f), (double)((float)(this.top + (int)this.amountScrolled) / f)).color(r, g, b, 255).endVertex();
      tessellator.draw();
   }

   protected void drawSelectionBox(int par1, int par2, int mouseXIn, int mouseYIn) {
      int i = this.getSize();
      Tessellator tessellator = Tessellator.getInstance();
      VertexBuffer worldrenderer = tessellator.getBuffer();

      for(int j = 0; j < i; ++j) {
         int k = par2 + j * this.slotHeight + this.headerPadding;
         int l = this.slotHeight - 4;
         if(k > this.bottom || k + l < this.top) {
            this.updateItemPos(j, par1, k);
         }

         if(this.showSelectionBox && this.isSelected(j) && k > this.top + 2 && k < this.bottom - 8) {
            int i1 = this.left + (this.width / 2 - this.getListWidth() / 2);
            int j1 = this.left + this.width / 2 + this.getListWidth() / 2;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableTexture2D();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int color = this.getEnabled()?(this.drawCustom?12165763:9482495):6316128;
            int r = color >> 16 & 255;
            int g = color >> 8 & 255;
            int b = color & 255;
            worldrenderer.pos((double)i1, (double)(k + l + 2), 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j1, (double)(k + l + 2), 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)j1, (double)(k - 2), 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)i1, (double)(k - 2), 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            color = this.getEnabled()?(this.drawCustom?13746081:80):6316128;
            r = color >> 16 & 255;
            g = color >> 8 & 255;
            b = color & 255;
            worldrenderer.pos((double)(i1 + 1), (double)(k + l + 1), 0.0D).tex(0.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)(j1 - 1), (double)(k + l + 1), 0.0D).tex(1.0D, 1.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)(j1 - 1), (double)(k - 1), 0.0D).tex(1.0D, 0.0D).color(r, g, b, 255).endVertex();
            worldrenderer.pos((double)(i1 + 1), (double)(k - 1), 0.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
         }

         this.drawSlot(j, par1, k, l, mouseXIn, mouseYIn);
      }

   }

   protected void drawSlot(int entryID, int x, int y, int slotHeight, int mouseX, int mouseY) {
      if(y > this.top + 2 && y < this.bottom - 8) {
         this.getListEntry(entryID).drawEntry(entryID, x, y, this.getListWidth(), slotHeight, mouseX, mouseY, this.getSlotIndexFromScreenCoords(mouseX, mouseY) == entryID);
      }

   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      if(this.getEnabled()) {
         super.elementClicked(slotIndex, isDoubleClick, mouseX, mouseY);
         this.selectedElement = slotIndex;
         if(this.parent != null) {
            if(isDoubleClick) {
               this.parent.listItemDoubleClicked(this, slotIndex);
            } else {
               this.parent.listItemSelected(this, slotIndex);
            }
         }
      }

   }

   public DMPGuiListboxEntry getListEntry(int slotIndex) {
      return (DMPGuiListboxEntry)this.listEntries.get(slotIndex);
   }

   public int getListWidth() {
      return this.width;
   }

   protected int getScrollBarX() {
      return this.right - 6;
   }

   public int getSize() {
      return this.listEntries.size();
   }

   public void handleMouseInput() {
      if(this.isMouseYWithinSlotBounds(this.mouseY) && this.getEnabled()) {
         if(Mouse.isButtonDown(0)) {
            if((float)this.initialClickY != -1.0F) {
               if((float)this.initialClickY >= 0.0F) {
                  this.amountScrolled -= ((float)this.mouseY - (float)this.initialClickY) * this.scrollMultiplier;
                  this.initialClickY = this.mouseY;
               }
            } else {
               boolean l1 = true;
               if(this.mouseY >= this.top && this.mouseY <= this.bottom) {
                  int i = this.left + 4;
                  int j = this.getScrollBarX() - 1;
                  int k = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                  int l = k / this.slotHeight;
                  if(this.mouseX >= i && this.mouseX <= j && l >= 0 && k >= 0 && l < this.getSize()) {
                     boolean i2 = l == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                     this.elementClicked(l, i2, this.mouseX, this.mouseY);
                     this.selectedElement = l;
                     this.lastClicked = Minecraft.getSystemTime();
                  } else if(this.mouseX >= i && this.mouseX <= j && k < 0) {
                     this.clickedHeader(this.mouseX - i, this.mouseY - this.top + (int)this.amountScrolled - 4);
                     l1 = false;
                  }

                  int i21 = this.getScrollBarX();
                  int i1 = this.right;
                  if(this.mouseX >= i21 && this.mouseX <= i1) {
                     this.scrollMultiplier = -1.0F;
                     int j1 = this.getMaxScroll();
                     if(j1 < 1) {
                        j1 = 1;
                     }

                     int k1 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                     k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
                     this.scrollMultiplier /= (float)(this.bottom - this.top - k1) / (float)j1;
                  } else {
                     this.scrollMultiplier = 1.0F;
                  }

                  if(l1) {
                     this.initialClickY = this.mouseY;
                  } else {
                     this.initialClickY = -2;
                  }
               } else {
                  this.initialClickY = -2;
               }
            }
         } else {
            this.initialClickY = -1;
         }

         int l11 = Mouse.getEventDWheel();
         if(l11 != 0) {
            if(l11 > 0) {
               l11 = -1;
            } else if(l11 < 0) {
               l11 = 1;
            }

            this.amountScrolled += (float)(l11 * this.slotHeight);
         }
      }

   }

   protected boolean isSelected(int slotIndex) {
      return this.selectedElement == slotIndex;
   }

   public int getSelectedItemIndex() {
      return this.selectedElement;
   }

   public String getSelectedItemKey() {
      if(this.selectedElement >= 0 && !this.listEntries.isEmpty()) {
         if(this.selectedElement > this.listEntries.size() - 1) {
            this.selectedElement = 0;
         }

         return ((DMPGuiListboxEntry)this.listEntries.get(this.selectedElement)).unlocalizedName;
      } else {
         return "";
      }
   }

   public String getSelectedItemText() {
      return this.selectedElement >= 0 && !this.listEntries.isEmpty()?((DMPGuiListboxEntry)this.listEntries.get(this.selectedElement)).localizedName:"";
   }

   public void selectListItem(int slotIndex) {
      if(slotIndex >= 0 && slotIndex < this.listEntries.size()) {
         this.selectedElement = slotIndex;
      }
   }

   public void saveListboxState(DMPGuiListboxState state) {
      state.amountScrolled = this.amountScrolled;
      state.scrollMultiplier = this.scrollMultiplier;
      state.selectedElement = this.selectedElement;
   }

   public void setListboxState(DMPGuiListboxState state) {
      this.amountScrolled = state.amountScrolled;
      this.scrollMultiplier = state.scrollMultiplier;
      this.selectedElement = state.selectedElement;
   }

   public void clearAllEntries() {
      this.listEntries.clear();
      this.selectedElement = 0;
   }

   public List getListEntries() {
      return this.listEntries;
   }

   public int getSlotFromKey(String key) {
      int size = this.listEntries.size();

      for(int i = 0; i < size; ++i) {
         if(((DMPGuiListboxEntry)this.listEntries.get(i)).unlocalizedName.matches(key)) {
            return i;
         }
      }

      return -1;
   }
}
