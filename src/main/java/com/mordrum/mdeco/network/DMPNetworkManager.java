package com.mordrum.mdeco.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DMPNetworkManager {
   public SimpleNetworkWrapper networkWrapper;

   public DMPNetworkManager() {
      this.networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("DMPMessages");
      this.registerMessages();
   }

   private void registerMessages() {
      this.networkWrapper.registerMessage(DMPPacketUpdateMarketCrate.Handler.class, DMPPacketUpdateMarketCrate.class, 0, Side.CLIENT);
      this.networkWrapper.registerMessage(DMPPacketUpdateMarketCrate.Handler.class, DMPPacketUpdateMarketCrate.class, 0, Side.SERVER);
      this.networkWrapper.registerMessage(DMPPacketUpdatePoleSign.Handler.class, DMPPacketUpdatePoleSign.class, 1, Side.CLIENT);
      this.networkWrapper.registerMessage(DMPPacketUpdatePoleSign.Handler.class, DMPPacketUpdatePoleSign.class, 1, Side.SERVER);
      this.networkWrapper.registerMessage(DMPPacketUpdateShopSign.Handler.class, DMPPacketUpdateShopSign.class, 2, Side.CLIENT);
      this.networkWrapper.registerMessage(DMPPacketUpdateShopSign.Handler.class, DMPPacketUpdateShopSign.class, 2, Side.SERVER);
   }
}
