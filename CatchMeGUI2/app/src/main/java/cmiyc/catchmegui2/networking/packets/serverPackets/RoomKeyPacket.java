package cmiyc.catchmegui2.networking.packets.serverPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class RoomKeyPacket extends Packet {

	public RoomKeyPacket(){
		putByte(Packet.ROOM_KEY);
	}
	
	public RoomKeyPacket(byte[] data){
		packet = data;
	}
	
	public void putRoomKey(String key){
		putString(key);
	}
	
	public String getRoomKey(){
		return getString();
	}
	
}