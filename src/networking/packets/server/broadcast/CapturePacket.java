package networking.packets.server.broadcast;

import networking.packets.ServerPacket;

public class CapturePacket extends ServerPacket {

	public CapturePacket(){
		putByte(BROADCAST);
		putByte(BROADCAST_CAPTURE);
	}
	
	public CapturePacket(byte[] data){
		packet = data;
	}
	
	public void putCapture(int targetID, int pursuerID){
		putInt(targetID);
		putInt(pursuerID);
	}
	
	public int[] getCapture(){
		return new int[]{getInt(), getInt()};
	}
	
}