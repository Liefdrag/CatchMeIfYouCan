package cmiyc.catchmegui2.networking.packets.clientPackets;

import cmiyc.catchmegui2.networking.packets.Packet;

public class ReportPacket extends Packet {

	public ReportPacket(){
		putByte(Packet.REPORT);
	}
	
	public ReportPacket(byte[] data){
		packet = data;
	}
	
	public void report(int playerID){
		putInt(playerID);
	}
	
	public int getReport(){
		return getInt();
	}
	
}