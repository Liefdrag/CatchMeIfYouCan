package networking.packets.server;

import networking.packets.ServerPacket;

public class RoomKeyPacket extends ServerPacket {

	public RoomKeyPacket(){
		putByte(ROOM_KEY);
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