package networking.packets.server;

import networking.packets.ServerPacket;

public class TargetPacket extends ServerPacket {

	public TargetPacket(){
		putByte(TARGET);
	}
	
	public TargetPacket(byte[] data){
		packet = data;
	}
	
	public void putTargetID(int targetID){
		putInt(targetID);
	}
	
	public int getTargetID(){
		return getInt();
	}
	
}