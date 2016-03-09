package networking.packets.server;

import networking.packets.ServerPacket;

public class HostPacket extends ServerPacket {

	public HostPacket(){
		putByte(HOST);
	}
	
}