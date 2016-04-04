package networking.packets.clientPackets;

import networking.packets.Packet;

public class ACKPacket extends Packet {

	public ACKPacket(){
		putByte(Packet.ACK);
	}
	
}