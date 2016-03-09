package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class TimeLimitPacket extends ServerPacket {

	public TimeLimitPacket(){
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_TIME_LIMIT);
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