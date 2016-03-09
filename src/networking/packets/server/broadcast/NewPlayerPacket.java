package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class NewPlayerPacket extends ServerPacket {

	public NewPlayerPacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_NEW_PLAYER);
	}
	
	public NewPlayerPacket(byte[] data){
		packet = data;
	}

	public void putPlayerName(String name){
		putString(name);
	}
	
	public String getPlayerName(){
		return getString();
	}
	
}