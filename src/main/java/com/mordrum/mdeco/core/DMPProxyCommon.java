package com.mordrum.mdeco.core;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.gui.DMPGuiHandler;
import com.mordrum.mdeco.network.DMPNetworkManager;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetWall;
import com.mordrum.mdeco.tileentity.DMPTileEntityCurioBase;
import com.mordrum.mdeco.tileentity.DMPTileEntityCurioTop;
import com.mordrum.mdeco.tileentity.DMPTileEntityMantle;
import com.mordrum.mdeco.tileentity.DMPTileEntitySofa;
import com.mordrum.mdeco.tileentity.DMPTileEntityWoodBox;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetBase;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetWallGlass;
import com.mordrum.mdeco.tileentity.DMPTileEntityDeskWoodBasic;
import com.mordrum.mdeco.tileentity.DMPTileEntityKitchenTableSetting;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketCrate;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketStand;
import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import com.mordrum.mdeco.tileentity.DMPTileEntityShelf;
import com.mordrum.mdeco.tileentity.DMPTileEntityShopSign;
import com.mordrum.mdeco.tileentity.DMPTileEntityWallLantern;
import com.mordrum.mdeco.tileentity.DMPTileEntityWoodCrate;
import com.mordrum.mdeco.tileentity.DMPTileEntityWorkbenchSaw;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DMPProxyCommon {
   public void preInit() {
      MDeco.networkManager = new DMPNetworkManager();
      MDeco.settings = new DMPSettings(MDeco.configPathRoot);
      MDeco.creativeTabs = new DMPCreativeTabs();
      MDeco.blocks = new DMPBlocks();
      MDeco.items = new DMPItems();
      MDeco.recipes = new DMPRecipes();
      GameRegistry.registerTileEntity(DMPTileEntityWorkbenchSaw.class, "workbenchSawTE");
      NetworkRegistry.INSTANCE.registerGuiHandler(MDeco.instance, new DMPGuiHandler());
      MinecraftForge.EVENT_BUS.register(MDeco.eventHandlers);

      // Register All Tile Entities
      GameRegistry.registerTileEntity(DMPTileEntityWoodBox.class, "woodBoxTE");
      GameRegistry.registerTileEntity(DMPTileEntityCabinetBase.class, "cabinetBaseTE");
      GameRegistry.registerTileEntity(DMPTileEntityCabinetWall.class, "cabinetWallTE");
      GameRegistry.registerTileEntity(DMPTileEntityCabinetWallGlass.class, "cabinetWallGlass");
      GameRegistry.registerTileEntity(DMPTileEntityCurioBase.class, "curioBaseTE");
      GameRegistry.registerTileEntity(DMPTileEntityCurioTop.class, "curioTopTE");
      GameRegistry.registerTileEntity(DMPTileEntityDeskWoodBasic.class, "deskWoodBasicTE");
      GameRegistry.registerTileEntity(DMPTileEntityKitchenTableSetting.class, "kitchenTableSettingTE");
      GameRegistry.registerTileEntity(DMPTileEntityMantle.class, "entityMantleTE");
      GameRegistry.registerTileEntity(DMPTileEntityMarketCrate.class, "marketCrateTE");
      GameRegistry.registerTileEntity(DMPTileEntityMarketStand.class, "marketStandTE");
      GameRegistry.registerTileEntity(DMPTileEntityPoleSign.class, "poleSignTE");
      GameRegistry.registerTileEntity(DMPTileEntityShelf.class, "shelfTE");
      GameRegistry.registerTileEntity(DMPTileEntityShopSign.class, "shopSignTE");
      GameRegistry.registerTileEntity(DMPTileEntitySofa.class, "sofaTE");
      GameRegistry.registerTileEntity(DMPTileEntityWallLantern.class, "wallLanternTE");
      GameRegistry.registerTileEntity(DMPTileEntityWoodCrate.class, "woodCrateTE");
   }

   public void init() {
      DMPReference.outputModStatisticsToConsole();
   }

   public void postInit() {
   }
}
