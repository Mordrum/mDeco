package com.mordrum.mdeco.core;

import com.mordrum.mdeco.MDeco;

public abstract class DMPReference {
   public static final String LOGGER_NAME = "MDeco";
   public static final String MOD_ID = "mdeco";
   public static final String MOD_NAME = "Decoration Mega Pack";
   public static final String MOD_VERSION = "1.9.4";
   public static final String GUI_FACTORY_CLASS = "mdeco.gui.DMPGuiFactory";
   public static final String PROXY_CLIENT = "mdeco.core.DMPProxyClient";
   public static final String PROXY_COMMON = "mdeco.core.DMPProxyCommon";
   public static final String PROXY_SERVER = "mdeco.core.DMPProxyServer";
   public static final boolean DEV_DEBUG_LOGGING = false;
   public static final boolean DEV_GEN_JSON_FILES = false;
   private static int statRegisteredBlocks = 0;
   private static int statRegisteredItems = 0;
   private static int statRegisteredRecipes = 0;

   public static void addToRegisteredBlocks(int quantity) {
      if(quantity > 0) {
         statRegisteredBlocks += quantity;
      }

   }

   public static void addToRegisteredItems(int quantity) {
      if(quantity > 0) {
         statRegisteredItems += quantity;
      }

   }

   public static void addToRegisteredRecipes(int quantity) {
      if(quantity > 0) {
         statRegisteredRecipes += quantity;
      }

   }

   public static int getRegisteredBlocks() {
      return statRegisteredBlocks;
   }

   public static int getRegisteredItems() {
      return statRegisteredItems;
   }

   public static int getRegisteredRecipes() {
      return statRegisteredRecipes;
   }

   public static void outputModStatisticsToConsole() {
      MDeco.instance.logOutput(String.format("Blocks : %d", new Object[]{Integer.valueOf(getRegisteredBlocks())}));
      MDeco.instance.logOutput(String.format("Items  : %d", new Object[]{Integer.valueOf(getRegisteredItems())}));
      MDeco.instance.logOutput(String.format("Recipes: %d", new Object[]{Integer.valueOf(getRegisteredRecipes())}));
   }
}
