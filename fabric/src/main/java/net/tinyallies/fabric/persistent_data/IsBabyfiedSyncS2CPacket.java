package net.tinyallies.fabric.persistent_data;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;

public class IsBabyfiedSyncS2CPacket {
	public static void receive(Minecraft client, ClientPacketListener handler,
			FriendlyByteBuf buf, PacketSender responseSender) {
		if(client.player !=null) {
			((IEntityDataSaver) client.player).getPersistentData().putBoolean("IsBabyfied", buf.readBoolean());
		}
	}
}
