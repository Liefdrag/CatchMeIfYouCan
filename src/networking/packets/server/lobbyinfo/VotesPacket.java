package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class VotesPacket extends ServerPacket {

	public VotesPacket(){
		putByte(LOBBYINFO);
		putByte(LOBBYINFO_VOTES);
	}
	
	public VotesPacket(byte[] data){
		packet = data;
	}
	
	public void VotesEnabled(){
		putByte(VOTES_ENABLED);
	}
	
	public void VotesDisabled(){
		putByte(VOTES_DISABLED);
	}
	
	public void putVotes(int[] votes){
		putInt(votes.length);
		for(int i = 0; i < votes.length; i++){
			putInt(votes[i]);
		}
	}
	
	public byte getVotesEnabled(){
		return getByte();
	}
	
	public int[] getVotes(){
		int length = getInt();
		int[] votes = new int[length];
		for(int i = 0; i < length; i++){
			votes[i] = getInt();
		}
		return votes;
	}
	
}