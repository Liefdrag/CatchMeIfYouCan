package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class NewHostPacket extends ServerPacket {

	public NewHostPacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_NEW_HOST);
	}
	
	public NewHostPacket(byte[] data){
		packet = data;
	}
	
	public void putPlayerID(int playerID){//the id of the new host
		putInt(playerID);
	}
	
	public int getPlayerID(){
		return getInt();
	}
	
}