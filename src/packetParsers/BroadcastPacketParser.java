package packetParsers;

import java.util.Arrays;

import game.Game;
import networking.packets.Packet;
import networking.packets.serverPackets.broadcastPackets.*;

public class BroadcastPacketParser {

	private Game game;

	public BroadcastPacketParser(Game game) {
		this.game = game;
	}

	public void processBroadcast(byte[] data) {
		byte broadcastID = data[0];
		data = Arrays.copyOfRange(data, 1, data.length);
		
		switch (broadcastID) {
		
		case Packet.BROADCAST_TIME_REMAINING :
			break;
			
		case Packet.BROADCAST_LEADERBOARD :
			System.out.println("Leaderboard Recieved: "+data.length);
			LeaderboardPacket lp = new LeaderboardPacket(data);
			lp.getLeaderboard();
			break;
			
		case Packet.BROADCAST_CAPTURE :
			break;
			
		case Packet.BROADCAST_VOTES :
			break;
			
		case Packet.BROADCAST_QUIT :
			break;
			
		case Packet.BROADCAST_BOUNDARY_UPDATE :
			break;
			
		case Packet.BROADCAST_NEW_HOST :
			break;
			
		case Packet.BROADCAST_NEW_PLAYER :
			NewPlayerPacket packet = new NewPlayerPacket();
			try {
				game.addPlayer(packet.getPlayerName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("New player broadcast recieved");
			break;
			
		case Packet.BROADCAST_PLAYER_READY :
			break;
			
		default :
			break;
		}
	}
	
}
