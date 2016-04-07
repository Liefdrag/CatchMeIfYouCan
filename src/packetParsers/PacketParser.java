package packetParsers;

import java.util.Arrays;

import networking.packets.*;

public class PacketParser {
	
	private String roomKey; //Used to determine which room to perform changes to
	private final BroadcastPacketParser broadcastParser;
	private final LobbyInfoPacketParser lobbyInfoParser;

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
		packet = Arrays.copyOf(packet, 1); //Removes the ID from the packet so only data is left
		
		//Each Case is a Protocol
		switch (dataID) {
		
		//Location of the Client
		case Packet.LOCATION :
			LocationPacket locationPacket = new LocationPacket(packet);
			break;
		
		case Packet.PING :
			break;
		
		case Packet.BROADCAST :
			broadcastParser.processBroadcast(dataID, packet);
			break;
		
		case Packet.TARGET :
			break;
		
		//May not be needed anymore
		case Packet.SPAWN_REGION :
			break;
				
		case Packet.ABILITY_ACTION :
			break;
				
		case Packet.GAME_START :
			break;
			
		case Packet.GAME_END :
			break;
			
		case Packet.ROOM_CLOSE :
			break;
		
		case Packet.ROOM_KEY :
			break;
		
		case Packet.LOBBYINFO :
			lobbyInfoParser.processLobbyInfo(dataID, packet);
			break;
			
		case Packet.KICK :
			break;
				
		case Packet.NAK :
			break;
			
		case Packet.HOST :
			break;
			
		case Packet.CAUGHT :
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
