package networking.packets.serverPackets.lobbyInfoPackets;

import networking.packets.Packet;

public class GametypePacket extends Packet{

	public GametypePacket(){
		putByte(Packet.LOBBYINFO);
		putByte(Packet.LOBBYINFO_GAMETYPE);
	}
	
	public GametypePacket(byte[] data){
		packet = data;
	}
	
	public void putGametype(byte gametype){
		putByte(gametype);
	}
	
	public byte getGametype(){
		return getByte();
	}
	
}