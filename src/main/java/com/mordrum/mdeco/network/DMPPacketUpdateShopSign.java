package com.mordrum.mdeco.network;

import com.mordrum.mdeco.tileentity.DMPTileEntityShopSign;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DMPPacketUpdateShopSign implements IMessage {
   private BlockPos pos;
   private String signText1 = "";
   private String signText2 = "";

   public DMPPacketUpdateShopSign() {
   }

   public DMPPacketUpdateShopSign(BlockPos pos, String signText1, String signText2) {
      this.pos = pos;
      this.signText1 = signText1;
      this.signText2 = signText2;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeLong(this.pos.toLong());
      ByteBufUtils.writeUTF8String(buffer, this.signText1);
      ByteBufUtils.writeUTF8String(buffer, this.signText2);
   }

   public void fromBytes(ByteBuf buffer) {
      this.pos = BlockPos.fromLong(buffer.readLong());
      this.signText1 = ByteBufUtils.readUTF8String(buffer);
      this.signText2 = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler implements IMessageHandler<DMPPacketUpdateShopSign, IMessage> {
      public IMessage onMessage(final DMPPacketUpdateShopSign message, final MessageContext ctx) {
         WorldServer mainThread = (WorldServer)ctx.getServerHandler().playerEntity.world;
         mainThread.addScheduledTask(new Runnable() {
            public void run() {
               DMPTileEntityShopSign tileentity = (DMPTileEntityShopSign)ctx.getServerHandler().playerEntity.world.getTileEntity(message.pos);
               tileentity.setSignText(message.signText1, message.signText2);
            }
         });
         return null;
      }
   }
}
