package cmiyc.catchmegui2.networking.packets.serverPackets.lobbyInfoPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class TimeLimitPacket extends Packet {

	public TimeLimitPacket(){
		putByte(Packet.LOBBYINFO);
		putByte(Packet.LOBBYINFO_TIME_LIMIT);
	}
	
	public TimeLimitPacket(byte[] data){
		packet = data;
	}
	
	public void putTimeLimit(int time){
		putInt(time);
	}
	
	public int getTimeLimit(){
		return getInt();
	}
	
}