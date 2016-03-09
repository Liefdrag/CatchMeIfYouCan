package networking.packets.server;

import networking.packets.ServerPacket;

public class GameEndPacket extends ServerPacket {

	public GameEndPacket(){
		putByte(GAME_END);
	}
	
}