package networking.packets.clientPackets;

import networking.packets.Packet;

public class CapturedPacket extends Packet {

	public CapturedPacket(){
		putByte(Packet.CAPTURED);
	}
	
}