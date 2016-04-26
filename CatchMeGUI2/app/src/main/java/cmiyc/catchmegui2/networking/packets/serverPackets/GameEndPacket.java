package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class GameEndPacket extends Packet {

	public GameEndPacket(){
		putByte(Packet.GAME_END);
	}
	
}