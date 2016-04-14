package packetParsers;

import java.util.Arrays;

import game.Game;
import main.CatchMeIfYouCanMain;
import networking.packets.*;
import networking.packets.serverPackets.*;

public class PacketParser {
	
	private String roomKey; //Used to determine which room to perform changes to
	private final BroadcastPacketParser broadcastParser;
	private final LobbyInfoPacketParser lobbyInfoParser;
	private Game game; // will be retrieved from Player/HostPlayer instance from the client

	public PacketParser() {
		broadcastParser = new BroadcastPacketParser();
		lobbyInfoParser = new LobbyInfoPacketParser();
	}
	
	/**
	 * Method for processing client packets
	 * @param packet - Byte data of the packet
	 */
	public void processPacket(byte[] packet) {
		byte dataID = packet[0]; //First byte is the host packet ID
		packet = Arrays.copyOfRange(packet, 1, packet.length); //Removes the ID from the packet so only data is left
		//Each Case is a Protocol
		switch (dataID) {
		
		//Location of the Client
		case Packet.LOCATION :
			LocationPacket locationPacket = new LocationPacket(packet);
			//Gets the Location as Coordinates and the Client ID (If its single player then the ID would correspond to target ID
			//If its a team mode, then the client would be able to choose which ID is his target
			break;
		
		case Packet.PING :
			PingPacket pp = new PingPacket();
			CatchMeIfYouCanMain.sendPacket(pp);
			//Sends the packet back to the Server
			break;
		
		case Packet.BROADCAST :
			broadcastParser.processBroadcast(packet);
			break;
		
		case Packet.TARGET :
			TargetPacket p = new TargetPacket(packet);
			game.setTarget(p.getTargetID());
			//Sets what the ID of the target is (All players locations are broadcast to everyone and so this ID chooses the ID
			break;
		
		//May not be needed anymore
		case Packet.SPAWN_REGION :
			break;
				
		case Packet.ABILITY_ACTION :
			AbilityUsagePacket abilityUsage = new AbilityUsagePacket(packet);
			//Get what the ability is
			break;
				
		case Packet.GAME_START :
			game.startGame();
			break;
			
		case Packet.GAME_END :
			game.endGame();
			break;
			
		case Packet.ROOM_CLOSE :
			game.closeRoom();
			break;
		
		case Packet.ROOM_KEY :
			//Sets the room key
			break;
		
		case Packet.LOBBYINFO :
			lobbyInfoParser.processLobbyInfo(dataID, packet);
			break;
			
		case Packet.KICK :
			KickPacket kickPacket = new KickPacket(packet);
			kickPacket.getKickReason(); //Do something with this reason
			game.removePlayer(game.getPlayerName());
			break;
				
		case Packet.NAK :
			//determine what the NAK is, i.e. wrong key, not enough players, game full/in progress.
			break;
			
		case Packet.HOST :
			game.setHost();
			break;
			
		case Packet.CAUGHT :
			game.playerCaught(game.getPlayerName());
			break;
		
		case Packet.JOIN_SUCCESS :
			JoinSuccessPacket joinPacket = new JoinSuccessPacket();
			break;
			
		default : 
			String bytes = dataID + " | ";
			for (int i = 0; i < packet.length; i ++) {
				bytes += packet[i] + " | ";
			}
			System.err.println("Unrecognised packet: \"" + bytes +
					" in room: " + roomKey);
			break;
		}
	}
	
	/**
	 * Method for setting the room key for both the client packet parser
	 * and the host action packet parser
	 * @param key - The Room Key
	 */
	public void setRoomKey(String key) {
		roomKey = key;
	}

}
