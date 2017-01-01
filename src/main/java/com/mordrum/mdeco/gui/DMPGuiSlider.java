package com.mordrum.mdeco.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DMPGuiSlider extends DMPGuiButton {
   DMPGuiBase parent;
   private String name;
   private String labelPrefix;
   private String labelSuffix;
   private float sliderValue;
   public boolean mouseButtonDown;
   private float valueMin;
   private float valueMax;
   private float valueStep;
   private DMPGuiSliderMode sliderMode;
   private boolean notifyParentOnChange;

   public DMPGuiSlider(DMPGuiBase parent, int id, int xPosition, int yPosition, int width, int height, String name, int valueMin, int valueInitial, int valueMax, int valueStep) {
      super(id, DMPGuiButtonMode.normal, xPosition, yPosition, width, height, name);
      this.parent = parent;
      this.name = name;
      this.labelPrefix = "";
      this.labelSuffix = "";
      this.sliderMode = DMPGuiSliderMode.normal;
      this.valueStep = (float)valueStep;
      this.valueMin = (float)valueMin;
      this.valueMax = (float)valueMax;
      this.sliderValue = this.normalizeValue((float)valueInitial);
      this.notifyParentOnChange = false;
      this.updateDisplayString();
   }

   public int getHoverState(boolean state) {
      return 0;
   }

   protected void mouseDragged(Minecraft mc, int xPos, int yPos) {
      if(this.visible) {
         if(this.mouseButtonDown) {
            this.sliderValue = (float)(xPos - (this.xPosition + 4)) / (float)(this.width - 8);
            if(this.sliderValue < 0.0F) {
               this.sliderValue = 0.0F;
            }

            if(this.sliderValue > 1.0F) {
               this.sliderValue = 1.0F;
            }

            this.sliderValue = this.normalizeValue((float)this.getIntValue());
            this.updateDisplayString();
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
         if(this.notifyParentOnChange) {
            this.parent.onSliderUpdate(this, this.getIntValue());
         }
      }

   }

   public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
      if(super.mousePressed(mc, xPos, yPos)) {
         this.sliderValue = (float)(xPos - (this.xPosition + 4)) / (float)(this.width - 8);
         if(this.sliderValue < 0.0F) {
            this.sliderValue = 0.0F;
         }

         if(this.sliderValue > 1.0F) {
            this.sliderValue = 1.0F;
         }

         this.updateDisplayString();
         this.mouseButtonDown = true;
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int xPos, int yPos) {
      this.mouseButtonDown = false;
   }

   private float normalizeValue(float value) {
      return MathHelper.clamp((this.snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
   }

   private float denormalizeValue(float value) {
      return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp(value, 0.0F, 1.0F));
   }

   private float snapToStepClamp(float value) {
      value = this.snapToStep(value);
      return MathHelper.clamp(value, this.valueMin, this.valueMax);
   }

   private float snapToStep(float value) {
      if(this.valueStep > 0.0F) {
         value = this.valueStep * (float)Math.round(value / this.valueStep);
      }

      return value;
   }

   public int getIntValue() {
      return (int)this.denormalizeValue(this.sliderValue);
   }

   public void setIntValue(int value) {
      if((float)value >= this.valueMin && (float)value <= this.valueMax) {
         this.sliderValue = this.normalizeValue((float)value);
      }
   }

   public void setLabelPrefix(String prefix) {
      this.labelPrefix = prefix;
   }

   public void setLabelSuffix(String suffix) {
      this.labelSuffix = suffix;
   }

   public void setSliderMode(DMPGuiSliderMode mode) {
      if(mode != null) {
         this.sliderMode = mode;
         if(this.sliderMode == DMPGuiSliderMode.percentage) {
            this.valueMax = 100.0F;
         }

      }
   }

   public void setNotifyParentOnUpdate(boolean notify) {
      this.notifyParentOnChange = notify;
   }

   public void updateDisplayString() {
      this.displayString = this.labelPrefix.isEmpty()?"":this.labelPrefix;
      if(this.sliderMode == DMPGuiSliderMode.normal) {
         this.displayString = this.displayString + I18n.translateToLocal(this.name) + ": ";
         this.displayString = this.displayString + (this.valueStep > 0.0F?(this.getIntValue() > 0?String.valueOf(this.getIntValue()):I18n.translateToLocal("options.off")):String.valueOf(this.getIntValue()));
         if(!this.labelSuffix.isEmpty()) {
            this.displayString = this.displayString + this.labelSuffix;
         }
      } else if(this.sliderMode == DMPGuiSliderMode.percentage) {
         this.displayString = this.displayString + I18n.translateToLocal(this.name) + ": ";
         this.displayString = this.displayString + (this.valueStep > 0.0F?(this.getIntValue() > 0?this.getIntValue() + "%":I18n.translateToLocal("options.off")):String.valueOf(this.getIntValue()));
      }

   }
}
