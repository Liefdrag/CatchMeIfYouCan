package networking.packets.serverPackets;

import networking.packets.Packet;

public class HostPacket extends Packet {

	public HostPacket(){
		putByte(Packet.HOST);
	}
	
}