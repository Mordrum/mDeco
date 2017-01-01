package com.mordrum.mdeco;

import com.mordrum.mdeco.core.DMPBlocks;
import com.mordrum.mdeco.core.DMPProxyCommon;
import com.mordrum.mdeco.core.DMPRecipes;
import com.mordrum.mdeco.network.DMPNetworkManager;
import com.mordrum.mdeco.core.DMPCreativeTabs;
import com.mordrum.mdeco.core.DMPEventHandlers;
import com.mordrum.mdeco.core.DMPItems;
import com.mordrum.mdeco.core.DMPSettings;
import com.mordrum.mdeco.json.DMPJsonGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
   modid = "mdeco",
   name = "mDeco",
//   version = "1.11",
   canBeDeactivated = false,
   guiFactory = "com.mordrum.mdeco.gui.DMPGuiFactory"
//   acceptedMinecraftVersions = "[1.11]"
)
public class MDeco {
   @Instance("mdeco")
   public static MDeco instance;
   @SidedProxy(
      clientSide = "com.mordrum.mdeco.core.DMPProxyClient",
      serverSide = "com.mordrum.mdeco.core.DMPProxyServer"
   )
   public static DMPProxyCommon proxy;
   private static final Logger logger = LogManager.getLogger("MDeco");
   public static String configPathRoot;
   public static DMPNetworkManager networkManager;
   public static DMPEventHandlers eventHandlers;
   public static DMPSettings settings;
   public static DMPBlocks blocks;
   public static DMPItems items;
   public static DMPCreativeTabs creativeTabs;
   public static DMPRecipes recipes;

   public MDeco() {
      eventHandlers = new DMPEventHandlers();
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      configPathRoot = event.getModConfigurationDirectory().getPath() + "/" + "mdeco" + "/";
      proxy.preInit();
      DMPJsonGenerator.generateJsonFiles(configPathRoot);
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      proxy.init();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      proxy.postInit();
   }

   public void logOutput(String line) {
      logger.info(line);
   }
}
