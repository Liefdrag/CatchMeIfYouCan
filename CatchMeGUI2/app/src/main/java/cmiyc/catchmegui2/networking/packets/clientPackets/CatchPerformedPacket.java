package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class CatchPerformedPacket extends Packet {

	public CatchPerformedPacket(){
		putByte(Packet.CATCH_PERFORMED);
	}
	
}