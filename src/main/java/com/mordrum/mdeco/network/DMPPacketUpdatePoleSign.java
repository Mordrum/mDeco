package com.mordrum.mdeco.network;

import com.mordrum.mdeco.tileentity.DMPTileEntityPoleSign;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DMPPacketUpdatePoleSign implements IMessage {
   private BlockPos pos;
   private String signText = "";

   public DMPPacketUpdatePoleSign() {
   }

   public DMPPacketUpdatePoleSign(BlockPos pos, String signText) {
      this.pos = pos;
      this.signText = signText;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeLong(this.pos.toLong());
      ByteBufUtils.writeUTF8String(buffer, this.signText);
   }

   public void fromBytes(ByteBuf buffer) {
      this.pos = BlockPos.fromLong(buffer.readLong());
      this.signText = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler implements IMessageHandler<DMPPacketUpdatePoleSign, IMessage> {
      public IMessage onMessage(final DMPPacketUpdatePoleSign message, final MessageContext ctx) {
         WorldServer mainThread = (WorldServer)ctx.getServerHandler().playerEntity.world;
         mainThread.addScheduledTask(new Runnable() {
            public void run() {
               DMPTileEntityPoleSign tileentity = (DMPTileEntityPoleSign)ctx.getServerHandler().playerEntity.world.getTileEntity(message.pos);
               tileentity.setSignText(message.signText);
            }
         });
         return null;
      }
   }
}
