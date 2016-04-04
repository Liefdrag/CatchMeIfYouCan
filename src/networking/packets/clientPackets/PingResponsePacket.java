package networking.packets.clientPackets;

import networking.packets.Packet;

public class PingResponsePacket extends Packet {
	
	public PingResponsePacket(){
		putByte(Packet.PING_RESPONSE);
	}
	
}