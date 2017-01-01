package com.mordrum.mdeco.gui;

public class DMPGuiListboxState {
   public float amountScrolled;
   public float scrollMultiplier;
   public int selectedElement;

   public DMPGuiListboxState() {
      this.resetState();
   }

   public DMPGuiListboxState(DMPGuiListboxState sourceState) {
      this.saveState(sourceState);
   }

   public void resetState() {
      this.amountScrolled = 0.0F;
      this.scrollMultiplier = 0.0F;
      this.selectedElement = 0;
   }

   public void saveState(DMPGuiListboxState sourceState) {
      this.amountScrolled = sourceState.amountScrolled;
      this.scrollMultiplier = sourceState.scrollMultiplier;
      this.selectedElement = sourceState.selectedElement;
   }
}
