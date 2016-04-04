package networking.packets.clientPackets;

import networking.packets.Packet;

public class BadSpawnPacket extends Packet {

	public BadSpawnPacket(){
		putByte(Packet.BAD_SPAWN);
	}
	
}