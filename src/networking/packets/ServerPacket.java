package networking.packets;

public class ServerPacket extends Packet {
	
	//ID Bytes
	//public static final byte SERVER_SENDER_ID = 0x01;
	public static final byte LOCATION = 0x01;
	public static final byte PING = 0x02;
	public static final byte BROADCAST = 0x03;
	public static final byte TARGET = 0x04;
	public static final byte SPAWN_REGION = 0x05;
	public static final byte ABILITY_USAGE = 0x06;
	public static final byte GAME_START = 0x07;
	public static final byte GAME_END = 0x08;
	public static final byte ROOM_CLOSE = 0x09;
	public static final byte ROOM_KEY = 0x0A;
	public static final byte LOBBYINFO = 0x0B;
	public static final byte KICK = 0x0C;
	public static final byte ACK = 0x0D;
	public static final byte NAK = 0x0E;
	public static final byte HOST = 0x0F;
	
	//Broadcast IDs
	public static final byte BROADCAST_TIME_REMAINING = 0x01;
	public static final byte BROADCAST_LEADERBOARD = 0x02;
	public static final byte BROADCAST_CAPTURE = 0x03;
	public static final byte BROADCAST_VOTES = 0x04;
	public static final byte BROADCAST_QUIT = 0x05;
	public static final byte BROADCAST_BOUNDARY_UPDATE = 0x06;
	public static final byte BROADCAST_NEW_HOST = 0x07;
	public static final byte BROADCAST_NEW_PLAYER = 0x08;
	public static final byte BROADCAST_PLAYER_READY = 0x09;
	
	//Lobby Information IDs
	public static final byte LOBBYINFO_GAMETYPE = 0x01;
	public static final byte LOBBYINFO_TIME_LIMIT = 0x02;
	public static final byte LOBBYINFO_SCORE_LIMIT = 0x03;
	public static final byte LOBBYINFO_BOUNDARIES = 0x04;
	public static final byte LOBBYINFO_LEADERBOARD = 0x05;
	public static final byte LOBBYINFO_ROOM_NAME = 0x06;
	public static final byte LOBBYINFO_VOTES = 0x07;
	
	public byte[] bytes;
	
	public ServerPacket() {}
	
	public static ServerPacket parse(byte[] bytes) {
		switch(bytes[1]){
			
			case LOCATION:
				return null;	//return packet
			case PING:
				return null;
			case BROADCAST:
				return null;
			case TARGET:
				return null;
			case SPAWN_REGION:
				return null;
			case ABILITY_USAGE:
				return null;
			case GAME_START:
				return null;
			case GAME_END:
				return null;
			case ROOM_CLOSE:
				return null;
			case ROOM_KEY:
				return null;
			case LOBBYINFO:
				//get 2nd byte
				return null;
			case KICK:
				return null;
			case ACK:
				return null;
			case NAK:
				return null;
			case HOST:
				return null;
			default:
				return null;
			
		}
	}
	
	public static ServerPacket parseLobbyInfo(byte[] bytes){
		switch(bytes[2]){
			
		}
		return null;
	}
	
	public static ServerPacket parseBroadcast(byte[] bytes){
		return null;
		
	}
	
	public void onPacketReceived() {	
	}
}
