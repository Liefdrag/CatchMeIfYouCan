package networking.packets.server;

import networking.packets.ServerPacket;

public class NAKPacket extends ServerPacket {
	
	public NAKPacket(){
		putByte(NAK);
	}
	
	public NAKPacket(byte[] data){
		packet = data;
	}
	
	public void setNAK(byte nak){
		putByte(nak);
	}
	
	public void invalidRoomKey(){
		putByte(NAK_INVALID_ROOM_KEY);
	}
	
	public void notEnoughPlayers(){
		putByte(NAK_NOT_ENOUGH_PLAYERS);
	}
	
	public byte getNAKID(){
		return getByte();
	}
	
}