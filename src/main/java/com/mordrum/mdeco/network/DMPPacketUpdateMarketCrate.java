package com.mordrum.mdeco.network;

import com.mordrum.mdeco.tileentity.DMPTileEntityMarketCrate;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DMPPacketUpdateMarketCrate implements IMessage {
   private BlockPos pos;
   private String displayText1 = "";
   private String displayText2 = "";

   public DMPPacketUpdateMarketCrate() {
   }

   public DMPPacketUpdateMarketCrate(BlockPos pos, String signText1, String signText2) {
      this.pos = pos;
      this.displayText1 = signText1;
      this.displayText2 = signText2;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeLong(this.pos.toLong());
      ByteBufUtils.writeUTF8String(buffer, this.displayText1);
      ByteBufUtils.writeUTF8String(buffer, this.displayText2);
   }

   public void fromBytes(ByteBuf buffer) {
      this.pos = BlockPos.fromLong(buffer.readLong());
      this.displayText1 = ByteBufUtils.readUTF8String(buffer);
      this.displayText2 = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler implements IMessageHandler<DMPPacketUpdateMarketCrate, IMessage> {
      public IMessage onMessage(final DMPPacketUpdateMarketCrate message, final MessageContext ctx) {
         WorldServer mainThread = (WorldServer)ctx.getServerHandler().playerEntity.world;
         mainThread.addScheduledTask(() -> {
            DMPTileEntityMarketCrate tileentity = (DMPTileEntityMarketCrate)ctx.getServerHandler().playerEntity.world.getTileEntity(message.pos);
            tileentity.setDisplayText(message.displayText1, message.displayText2);
         });
         return null;
      }
   }
}
