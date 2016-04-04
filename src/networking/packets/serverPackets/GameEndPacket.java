package networking.packets.serverPackets;

import networking.packets.Packet;

public class GameEndPacket extends Packet {

	public GameEndPacket(){
		putByte(Packet.GAME_END);
	}
	
}