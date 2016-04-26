package cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class CloseRoomPacket extends Packet {

	public CloseRoomPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_CLOSE_ROOM);
	}
	
}