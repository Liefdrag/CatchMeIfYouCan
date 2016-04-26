package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class RoomClosePacket extends Packet {

	public RoomClosePacket(){
		putByte(Packet.ROOM_CLOSE);
	}
	
}