package cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class KickPlayerPacket extends Packet {

	public KickPlayerPacket(){
		putByte(Packet.HOST_ACTION);
		putByte(Packet.HOST_ACTION_KICK_PLAYER);
	}
	
	public KickPlayerPacket(byte[] data){
		packet = data;
	}
	
	public void putPlayerID(int playerID){
		putInt(playerID);
	}
	
	public int getPlayerID(){
		return getInt();
	}
	
}