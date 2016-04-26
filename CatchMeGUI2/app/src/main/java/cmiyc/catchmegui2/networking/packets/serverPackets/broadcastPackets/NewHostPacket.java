package cmiyc.catchmegui2.networking.packets.serverPackets.broadcastPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class NewHostPacket extends Packet {

	public NewHostPacket(){
		putByte(Packet.BROADCAST);
		putByte(Packet.BROADCAST_NEW_HOST);
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