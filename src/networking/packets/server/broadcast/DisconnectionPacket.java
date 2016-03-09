package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class DisconnectionPacket extends ServerPacket {
	
	public DisconnectionPacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_QUIT);
	}
	
	public DisconnectionPacket(byte[] data){
		packet = data;
	}
	
	public void putPlayerID(int playerID){
		putInt(playerID);
	}
	
	//Have seperate function for each reason or just have byte passed to packet?
	public void putReasonQuit(){
		putByte(DISCONNECT_QUIT);
	}
	
	public void putReasonPoorConnection(){
		putByte(DISCONNECT_POOR_CONNECTION);
	}
	
	public void putReasonKick(){
		putByte(DISCONNECT_KICK);
	}
	
	public int getPlayerID(){
		return getInt();
	}
	
	public byte getDisconnectReason(){
		return getByte();
	}

}