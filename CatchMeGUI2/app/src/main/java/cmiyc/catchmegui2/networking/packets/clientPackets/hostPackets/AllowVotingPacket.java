package cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class AllowVotingPacket extends Packet {

	public AllowVotingPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_ALLOW_VOTING);
	}
	
}