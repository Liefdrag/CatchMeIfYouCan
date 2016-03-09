package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class TimeRemainingPacket extends ServerPacket {

	public TimeRemainingPacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_TIME_REMAINING);
	}
	
	public TimeRemainingPacket(byte[] data){
		packet = data;
	}
	
	public void putTimeReaming(int minutes, int seconds){
		putInt(minutes);
		putInt(seconds);
	}
	
	public int[] getTimeRemaining(){
		return new int[]{getInt(), getInt()};
	}
	
}