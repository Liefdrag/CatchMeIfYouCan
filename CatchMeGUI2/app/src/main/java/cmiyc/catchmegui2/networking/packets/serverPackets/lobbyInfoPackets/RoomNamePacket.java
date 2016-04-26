package cmiyc.catchmegui2.networking.packets.serverPackets.lobbyInfoPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class RoomNamePacket extends Packet {

	public RoomNamePacket(){
		putByte(Packet.LOBBYINFO);
		putByte(Packet.LOBBYINFO_ROOM_NAME);
	}
	
	public RoomNamePacket(byte[] data){
		packet = data;
	}
	
	public void putRoomName(String name){
		putString(name);
	}
	
	public String getRoomName(){
		return getString();
	}
	
}