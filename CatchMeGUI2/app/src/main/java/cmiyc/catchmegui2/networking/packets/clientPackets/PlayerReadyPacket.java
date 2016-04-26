package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class PlayerReadyPacket extends Packet {

	public PlayerReadyPacket(){
		putByte(Packet.PLAYER_READY);
	}
	
}