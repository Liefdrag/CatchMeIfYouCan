package packetParsers;

import java.util.Arrays;

import networking.packets.Packet;

public class BroadcastPacketParser {

	public void processBroadcast(int dataID, byte[] data) {
		byte broadcastID = data[0];
		data = Arrays.copyOf(data, 1);
		
		switch (broadcastID) {
		
		case Packet.BROADCAST_TIME_REMAINING :
			break;
			
		case Packet.BROADCAST_LEADERBOARD :
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
			break;
			
		case Packet.BROADCAST_PLAYER_READY :
			break;
			
		default :
			break;
		}
	}
	
}
