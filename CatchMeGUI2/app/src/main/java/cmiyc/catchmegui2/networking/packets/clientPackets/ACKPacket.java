package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class ACKPacket extends Packet {

	public ACKPacket(){
		putByte(Packet.ACK);
	}
	
}