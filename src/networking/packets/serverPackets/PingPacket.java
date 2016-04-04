package networking.packets.serverPackets;

import networking.packets.Packet;

public class PingPacket extends Packet {

	public PingPacket(){
		putByte(Packet.PING);
	}
	
}