package networking.packets.serverPackets.lobbyInfoPackets;

import networking.packets.Packet;

public class ScoreLimitPacket extends Packet {

	public ScoreLimitPacket(){
		putByte(Packet.LOBBYINFO);
		putByte(Packet.LOBBYINFO_SCORE_LIMIT);
	}
	
	public ScoreLimitPacket(byte[] data){
		packet = data;
	}
	
	public void putScoreLimit(int score){
		putInt(score);
	}
	
	public int getScoreLimit(){
		return getInt();
	}
	
}