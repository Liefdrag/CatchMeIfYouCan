package networking.packets.serverPackets;

import networking.packets.Packet;

public class GameStartPacket extends Packet {

	public GameStartPacket(){
		putByte(Packet.GAME_START);
	}
	
}