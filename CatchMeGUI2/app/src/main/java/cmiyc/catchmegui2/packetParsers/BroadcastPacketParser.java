package cmiyc.catchmegui2.packetParsers;

import java.util.Arrays;

import cmiyc.catchmegui2.game.Game;
import cmiyc.catchmegui2.networking.packets.Packet;
import cmiyc.catchmegui2.networking.packets.serverPackets.broadcastPackets.*;

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
			TimeRemainingPacket timeRemainingPacket = new TimeRemainingPacket(data);
			//alert player of time left
			break;

		case Packet.BROADCAST_LEADERBOARD :
			System.out.println("Leaderboard Recieved: "+data.length);
			LeaderboardPacket lp = new LeaderboardPacket(data);
			lp.getLeaderboard();
			break;

		case Packet.BROADCAST_CAPTURE :
			CapturePacket capturePacket = new CapturePacket(data);
			//game.playerCaught(capturePacket.getCapture()); //need to convert id to name, or change method
			break;

		case Packet.BROADCAST_VOTES :
			VotesPacket votesPacket = new VotesPacket(data);
			votesPacket.getVotes();
			// do something
			break;

		case Packet.BROADCAST_QUIT :
			DisconnectionPacket disconnectionPacket = new DisconnectionPacket(data);
			try {
				game.removePlayer(game.getPlayerNameFromID(disconnectionPacket.getPlayerID()), disconnectionPacket.getDisconnectReason());
			}catch(Exception e){
				e.printStackTrace();
			}
			// also need to convert id to name
			break;

		case Packet.BROADCAST_BOUNDARY_UPDATE :
			BoundaryUpdatePacket boundaryUpdatePacket = new BoundaryUpdatePacket(data);
			double[] centre = boundaryUpdatePacket.getBoundariesCentre();
			double radius = boundaryUpdatePacket.getBoundariesRadius();
			game.updateBoundary(centre, radius);
			break;

		case Packet.BROADCAST_NEW_HOST :
			NewHostPacket newHostPacket = new NewHostPacket(data);
			if(game.playerID == newHostPacket.getPlayerID()) // change to ids or names
				game.setHost();
			//else alert of new host?
			break;

		case Packet.BROADCAST_NEW_PLAYER :
			NewPlayerPacket packet = new NewPlayerPacket(data);
			try {
				game.addPlayer(packet.getPlayerName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default :
			break;
		}
	}

}
