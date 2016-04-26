package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class CapturedPacket extends Packet {

	public CapturedPacket(){
		putByte(Packet.CAPTURED);
	}
	
}