package networking.packets.serverPackets;

import networking.packets.Packet;

public class RoomClosePacket extends Packet {

	public RoomClosePacket(){
		putByte(Packet.ROOM_CLOSE);
	}
	
}