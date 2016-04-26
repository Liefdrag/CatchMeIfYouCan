package packetParsers;

import java.util.Arrays;

import game.Game;
import networking.packets.*;
import networking.packets.serverPackets.lobbyInfoPackets.*;

public class LobbyInfoPacketParser {
	
	private Game game;

	public LobbyInfoPacketParser(Game game) {
		this.game = game;
	}

	public void processLobbyInfo(int dataID, byte[] data) {
		byte lobbyInfoID = data[0];
		data = Arrays.copyOfRange(data, 1, data.length);
		
		switch (lobbyInfoID) {
		
		case Packet.LOBBYINFO_GAMETYPE :
			GametypePacket gtp = new GametypePacket();
			game.updateLobbyInfo("GAME_TYPE", gtp.getGametype());
			//Sets the ID of the Game Mode
			break;
			
		case Packet.LOBBYINFO_TIME_LIMIT :
			TimeLimitPacket tlp = new TimeLimitPacket();
			game.updateLobbyInfo("TIME_LIMIT", tlp.getTimeLimit());
			break;
			
		case Packet.LOBBYINFO_SCORE_LIMIT :
			ScoreLimitPacket slp = new ScoreLimitPacket();
			game.updateLobbyInfo("SCORE_LIMIT", slp.getScoreLimit());
			break;
			
		case Packet.LOBBYINFO_BOUNDARIES :
			BoundariesPacket bp = new BoundariesPacket();
			bp.getBoundariesCentre(); //Sets the centre coordinates of the boundary
			bp.getBoundariesRadius(); //Sets the radius of the boundary
			break;
			
		case Packet.LOBBYINFO_LEADERBOARD :
			LeaderboardPacket lp = new LeaderboardPacket();
			lp.getLeaderboard(); //Sets the lobby leaderboard
			
			break;
			
		case Packet.LOBBYINFO_ROOM_NAME :
			RoomNamePacket rnp = new RoomNamePacket();
			rnp.getRoomName(); //Sets the room name
			break;
			
		case Packet.LOBBYINFO_VOTES :
			VotesPacket vp = new VotesPacket();
			vp.getVotes(); //Sets the vote count
			break;
			
		default :
			break;
		}
		
		
		}
	
	
}
