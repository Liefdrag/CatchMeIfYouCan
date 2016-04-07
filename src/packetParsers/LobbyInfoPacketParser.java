package packetParsers;

import java.util.Arrays;

import networking.packets.*;

public class LobbyInfoPacketParser {
	
	public void processLobbyInfo(int dataID, byte[] data) {
		byte lobbyInfoID = data[0];
		data = Arrays.copyOf(data, 1);
		
		switch (lobbyInfoID) {
		
		case Packet.LOBBYINFO_GAMETYPE :
			break;
			
		case Packet.LOBBYINFO_TIME_LIMIT :
			break;
			
		case Packet.LOBBYINFO_SCORE_LIMIT :
			break;
			
		case Packet.LOBBYINFO_BOUNDARIES :
			break;
			
		case Packet.LOBBYINFO_LEADERBOARD :
			break;
			
		case Packet.LOBBYINFO_ROOM_NAME :
			break;
			
		case Packet.LOBBYINFO_VOTES :
			break;
			
		default :
			break;
		}
		
		
		}
	
	
}
