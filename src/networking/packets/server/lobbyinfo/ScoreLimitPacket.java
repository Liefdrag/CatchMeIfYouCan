package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class ScoreLimitPacket extends ServerPacket {

	public ScoreLimitPacket(){
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_SCORE_LIMIT);
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