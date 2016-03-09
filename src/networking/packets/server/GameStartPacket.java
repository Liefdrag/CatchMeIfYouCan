package networking.packets.server;

import networking.packets.ServerPacket;

public class GameStartPacket extends ServerPacket {

	public GameStartPacket(){
		putByte(GAME_START);
	}
	
}