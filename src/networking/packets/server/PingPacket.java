package networking.packets.server;

import networking.packets.ServerPacket;

public class PingPacket extends ServerPacket {

	public PingPacket(){
		putByte(PING);
	}
	
}