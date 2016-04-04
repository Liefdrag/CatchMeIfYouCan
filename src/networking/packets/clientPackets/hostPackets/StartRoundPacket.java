package networking.packets.clientPackets.hostPackets;

import networking.packets.Packet;

public class StartRoundPacket extends Packet {

	public StartRoundPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_START_ROUND);
	}
	
}