package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class GameStartPacket extends Packet {

	public GameStartPacket(){
		putByte(Packet.GAME_START);
	}
	
}