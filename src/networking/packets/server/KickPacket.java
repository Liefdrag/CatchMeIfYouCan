package networking.packets.server;

import networking.packets.ServerPacket;

public class KickPacket extends ServerPacket {
	
	public KickPacket(){
		putByte(KICK);
	}
	
	public KickPacket(byte[] data){
		packet = data;
	}
	
	public void putKickReason(byte reason){
		putByte(reason);
	}
	
	//Needed?
//	public void putReasonKicked(){
//		putByte(Packet.KICK_KICKED);
//	}
//	
//	public void putReasonPoorConnection(){
//		putByte(Packet.KICK_POOR_CONNECTION);
//	}
	
	public byte getKickReason(){
		return getByte();
	}
	
}