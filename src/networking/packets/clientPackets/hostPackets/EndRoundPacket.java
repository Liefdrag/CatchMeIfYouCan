package networking.packets.clientPackets.hostPackets;

import networking.packets.Packet;

public class EndRoundPacket extends Packet {

	public EndRoundPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_END_ROUND);
	}
	
}