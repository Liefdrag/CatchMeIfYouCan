package cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class EndRoundPacket extends Packet {

	public EndRoundPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_END_ROUND);
	}
	
}