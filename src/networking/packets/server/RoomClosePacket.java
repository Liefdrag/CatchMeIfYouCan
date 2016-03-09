package networking.packets.server;

import networking.packets.ServerPacket;

public class RoomClosePacket extends ServerPacket {

	public RoomClosePacket(){
		putByte(ROOM_CLOSE);
	}
	
}