package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class PingResponsePacket extends Packet {
	
	public PingResponsePacket(){
		putByte(Packet.PING_RESPONSE);
	}
	
}