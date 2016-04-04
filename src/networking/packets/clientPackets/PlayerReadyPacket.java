package networking.packets.clientPackets;

import networking.packets.Packet;

public class PlayerReadyPacket extends Packet {

	public PlayerReadyPacket(){
		putByte(Packet.PLAYER_READY);
	}
	
}