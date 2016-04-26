package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class PingPacket extends Packet {

	public PingPacket(){
		putByte(Packet.PING);
	}
	
}