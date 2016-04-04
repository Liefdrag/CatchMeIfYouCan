package networking.packets.clientPackets;

import networking.packets.Packet;

public class CatchPerformedPacket extends Packet {

	public CatchPerformedPacket(){
		putByte(Packet.CATCH_PERFORMED);
	}
	
}