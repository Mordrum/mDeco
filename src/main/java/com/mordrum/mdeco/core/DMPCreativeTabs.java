package com.mordrum.mdeco.core;

import com.mordrum.mdeco.gui.DMPGuiCreativeTab;
import com.mordrum.mdeco.object.DMPTab;

import java.util.HashMap;

public class DMPCreativeTabs {
   private HashMap creativeTabs = new HashMap(DMPTab.values().length);

   public DMPCreativeTabs() {
      DMPTab[] var2 = DMPTab.values();
      int var3 = var2.length;

	   for (DMPTab tab : var2) {
		   DMPGuiCreativeTab newTab = new DMPGuiCreativeTab(tab);
		   this.creativeTabs.put(tab.name(), newTab);
	   }

   }

   public DMPGuiCreativeTab getCreativeTab(DMPTab tab) {
      return tab != null?(DMPGuiCreativeTab)this.creativeTabs.get(tab.name()):null;
   }
}
