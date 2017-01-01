package com.mordrum.mdeco.core;

import com.mordrum.mdeco.render.DMPRenderMantle;
import com.mordrum.mdeco.render.DMPRenderMarketStand;
import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.render.DMPRenderCabinetBase;
import com.mordrum.mdeco.render.DMPRenderCabinetWallGlass;
import com.mordrum.mdeco.render.DMPRenderCurioBase;
import com.mordrum.mdeco.render.DMPRenderCurioTop;
import com.mordrum.mdeco.render.DMPRenderKitchenTableSetting;
import com.mordrum.mdeco.render.DMPRenderMarketCrate;
import com.mordrum.mdeco.render.DMPRenderPoleSign;
import com.mordrum.mdeco.render.DMPRenderShelf;
import com.mordrum.mdeco.render.DMPRenderShopSign;
import com.mordrum.mdeco.render.DMPRenderSofa;
import com.mordrum.mdeco.render.DMPRenderWallLantern;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetBase;
import com.mordrum.mdeco.tileentity.DMPTileEntityCabinetWallGlass;
import com.mordrum.mdeco.tileentity.DMPTileEntityCurioBase;
import com.mordrum.mdeco.tileentity.DMPTileEntityCurioTop;
import com.mordrum.mdeco.tileentity.DMPTileEntityKitchenTableSetting;
import com.mordrum.mdeco.tileentity.DMPTileEntityMantle;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketCrate;
import com.mordrum.mdeco.tileentity.DMPTileEntityMarketStand;
import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import com.mordrum.mdeco.tileentity.DMPTileEntityShelf;
import com.mordrum.mdeco.tileentity.DMPTileEntityShopSign;
import com.mordrum.mdeco.tileentity.DMPTileEntitySofa;
import com.mordrum.mdeco.tileentity.DMPTileEntityWallLantern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class DMPProxyClient extends DMPProxyCommon {
   public void preInit() {
      super.preInit();
      MDeco.blocks.preInit();
   }

   public void init() {
      super.init();
      MDeco.blocks.init();
      MDeco.items.init();
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityCabinetBase.class, new DMPRenderCabinetBase(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityCabinetWallGlass.class, new DMPRenderCabinetWallGlass(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityCurioBase.class, new DMPRenderCurioBase(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityCurioTop.class, new DMPRenderCurioTop(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityShelf.class, new DMPRenderShelf(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityKitchenTableSetting.class, new DMPRenderKitchenTableSetting(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityMantle.class, new DMPRenderMantle(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityMarketCrate.class, new DMPRenderMarketCrate(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityMarketStand.class, new DMPRenderMarketStand(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityPoleSign.class, new DMPRenderPoleSign(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityShopSign.class, new DMPRenderShopSign(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntitySofa.class, new DMPRenderSofa(renderManager, renderItem));
      ClientRegistry.bindTileEntitySpecialRenderer(DMPTileEntityWallLantern.class, new DMPRenderWallLantern(renderManager, renderItem));
   }

   public void postInit() {
      super.postInit();
   }
}
