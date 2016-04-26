package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class BadSpawnPacket extends Packet {

	public BadSpawnPacket(){
		putByte(Packet.BAD_SPAWN);
	}
	
}