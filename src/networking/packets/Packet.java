package networking.packets;

import java.nio.ByteBuffer;
import java.util.Arrays;

import networking.packets.serverPackets.*;
import networking.packets.serverPackets.broadcastPackets.*;
import networking.packets.serverPackets.lobbyInfoPackets.*;

public abstract class Packet {// will need to test for construction of packets,
								// worked however when testing individually

	protected byte[] packet = new byte[0];

	
	
	protected byte getByte() {
		byte byte_ = packet[0];
		packet = Arrays.copyOfRange(packet, 1, packet.length);

		// printPacket();

		return byte_;
	}

	protected void putByte(byte byte_) {
		packet = Arrays.copyOf(packet, packet.length + 1);
		packet[packet.length - 1] = byte_;

		// printPacket();
	}

	protected char getChar() {
		char character;
		byte[] character_ = new byte[] { packet[0], packet[1] };
		character = ByteBuffer.wrap(character_).getChar();
		packet = Arrays.copyOfRange(packet, 2, packet.length);

		// printPacket();

		return character;
	}

	protected void putChar(char character) {
		byte[] character_ = new byte[2];
		ByteBuffer.wrap(character_).putChar(character);
		packet = Arrays.copyOf(packet, packet.length + 2);
		packet[packet.length - 2] = character_[0];
		packet[packet.length - 1] = character_[1];

		// printPacket();
	}

	protected double getDouble() {
		double d_;
		byte[] double_;

		double_ = Arrays.copyOf(packet, 8);
		d_ = ByteBuffer.wrap(double_).getDouble();
		packet = Arrays.copyOfRange(packet, 8, packet.length);

		// printPacket();

		return d_;
	}

	protected void putDouble(double d_) {
		byte[] double_ = new byte[8];
		ByteBuffer.wrap(double_).putDouble(d_);

		packet = Arrays.copyOf(packet, packet.length + 8);

		for (int i = 0; i < 8; i++) {
			packet[packet.length - (8 - i)] = double_[i];
		}

		// printPacket();
	}

	protected int getInt() {
		byte[] int_;
		int integer;
		System.out.println(toString());
		int_ = Arrays.copyOf(packet, 4);
		integer = ByteBuffer.wrap(int_).getInt();
		packet = Arrays.copyOfRange(packet, 4, packet.length);

		// printPacket();

		return integer;
	}

	protected void putInt(int integer) {
		//Int Data Type occupies four bytes of space
		byte[] int_ = ByteBuffer.allocate(4).putInt(integer).array();
		
		packet = Arrays.copyOf(packet, packet.length + 4);
		
		for(int i = 0; i < 4; i ++){
			packet[packet.length - (4 - i)] = int_[i];
		}
		
		//printPacket();
	}

	protected void putString(String string) {// need to include padding in the
												// event the string is not of a
												// preset size.
		char[] string_ = string.toCharArray();
		int length = string.length();
		putInt(length);// putting the length of the string first
		for (int i = 0; i < length; i++) {
			putChar(string_[i]);
		}
	}

	protected String getString() {
		String string_ = "";
		int length = getInt();// getting the length of the string to know how
								// much to read
		for (int i = 0; i < length; i++) {
			string_ += getChar();
		}

		return string_;
	}

	public byte[] getPacket() {// used to perform a checksum on a packet and
								// return it.
		// byte[] newPacket = new byte[packet.length + 1];
		// byte checksum = 0x00;
		//
		// for(int i = 0; i < packet.length; i++){
		// checksum += packet[i];
		// newPacket[i] = packet[i];
		// }
		// newPacket[newPacket.length - 1] = checksum; //adding the checksum to
		// the packet.
		//
		// System.out.println(toString() + checksum + " | ");
		//
		// return newPacket;
		return packet;
	}

	@Override
	public String toString() {
		String bytes = " | ";
		for (int i = 0; i < packet.length; i++) {
			bytes += packet[i] + " | ";
		}
		return bytes;
	}

	private void printPacket() {
		System.out.println(toString());
	}

	// IDs are the same for some packets. These need to be changed.
	public static Packet parse(byte[] bytes) {
		switch (bytes[0]) {
			case LOCATION:
				return new LocationPacket(); // return packet
			case PING:
				return new PingPacket();
			case BROADCAST:
				return parseBroadcast(bytes);
			case TARGET:
				return new TargetPacket();
			case SPAWN_REGION:
				return new SpawnRegionPacket();
			case ABILITY_ACTION:
				return new AbilityUsagePacket();
			case GAME_START:
				return new GameStartPacket();
			case GAME_END:
				return new GameEndPacket();
			case ROOM_CLOSE:
				return new RoomClosePacket();
			case ROOM_KEY:
				return new RoomKeyPacket();
			case LOBBYINFO:
				return parseLobbyInfo(bytes);
			case KICK:
				return new KickPacket();
			case NAK:
				return new NAKPacket();
			case HOST:
				return new HostPacket();
			default:
				return null;
		}
	}

	// Some LobbyInfo & Broadcast packets are the same, this is a problem
	// Some packets with IDs don't exist.
	private static Packet parseBroadcast(byte[] bytes) {
		switch (bytes[0]) {
			case BROADCAST_TIME_REMAINING:
				return new TimeRemainingPacket();
			case BROADCAST_LEADERBOARD:
				return new networking.packets.serverPackets.broadcastPackets.LeaderboardPacket();
			case BROADCAST_CAPTURE:
				return new CapturePacket();
			case BROADCAST_VOTES:
				return new networking.packets.serverPackets.broadcastPackets.VotesPacket();
			case BROADCAST_QUIT:
				return new DisconnectionPacket();
			case BROADCAST_BOUNDARY_UPDATE:
				return new BoundaryUpdatePacket();
			case BROADCAST_NEW_HOST:
				return new NewHostPacket();
			case BROADCAST_NEW_PLAYER:
				return new NewPlayerPacket();
//			case BROADCAST_PLAYER_READY:
//				return new PlayerReadyPacket();
			default:
				return null;
		}
	}

	private static Packet parseLobbyInfo(byte[] bytes) {
		switch (bytes[0]) {
			case LOBBYINFO_GAMETYPE:
				return new GametypePacket();
			case LOBBYINFO_TIME_LIMIT:
				return new TimeLimitPacket();
			case LOBBYINFO_SCORE_LIMIT:
				return new ScoreLimitPacket();
			case LOBBYINFO_BOUNDARIES:
				return new BoundariesPacket();
			case LOBBYINFO_LEADERBOARD:
				return new networking.packets.serverPackets.lobbyInfoPackets.LeaderboardPacket();
			case LOBBYINFO_ROOM_NAME:
				return new RoomNamePacket();
			case LOBBYINFO_VOTES:
				return new networking.packets.serverPackets.lobbyInfoPackets.VotesPacket();
			default:
				return null;
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * PACKET DATA IDs
	 */

	// Ability IDs

	// Hides the player location from their pursuer
	public static final byte ABILITY_HIDE = 0X01;
	// Produces beeps on target's device, i.e. if nearby you can hear the beep
	// and find the target
	public static final byte ABILITY_PING = 0X02;
	// Sends a fake location to the pursuer for x amount of time or until
	// pursuer enters within y meters of decoy
	public static final byte ABILITY_DECOY = 0X03;
	// Target not alerted when pursuer is within x meters of target
	public static final byte ABILITY_SNEAK = 0X04;

	// Gametype IDs
	public static final byte GAMETYPE_DEFAULT = 0X01;
	public static final byte GAMETYPE_TEAM = 0X02;
	public static final byte GAMETYPE_MAN_HUNT = 0X03;

	// NAK IDs
	public static final byte NAK_INVALID_ROOM_KEY = 0x01;
	public static final byte NAK_NOT_ENOUGH_PLAYERS = 0x02;
	public static final byte NAK_ROOM_FULL = 0x03;
	public static final byte NAK_NO_VALID_TARGETS = 0x04;

	// Disconnect IDs
	public static final byte DISCONNECT_QUIT = 0X01;
	public static final byte DISCONNECT_POOR_CONNECTION = 0X02;
	public static final byte DISCONNECT_KICK = 0X03;

	// Votes IDs
	public static final byte VOTES_ENABLED = 0x01;
	public static final byte VOTES_DISABLED = 0x02;

	// Kick IDs
	public static final byte KICK_POOR_CONNECTION = 0X01;
	public static final byte KICK_KICKED = 0X02;

	// Location ID -- same for server and client
	public static final byte LOCATION = 0x01;

	/*----------------------------------------------------------*/

	// Client IDs
	public static final byte PING_RESPONSE = 0x02;
	public static final byte CATCH_PERFORMED = 0x03;
	public static final byte CAPTURED = 0x04;
	public static final byte ABILITY_USAGE = 0x05;
	public static final byte VOTE = 0x06;
	public static final byte REPORT = 0x07;
	public static final byte QUIT = 0x08;
	public static final byte JOIN = 0x09;
	public static final byte HOST_ACTION = 0x0A;
	public static final byte ACK = 0x0B;
	public static final byte BAD_SPAWN = 0x0C;
	public static final byte PLAYER_READY = 0x0D;

	// Client Host Action IDs
	public static final byte HOST_ACTION_START_ROUND = 0x01;
	public static final byte HOST_ACTION_END_ROUND = 0x02;
	public static final byte HOST_ACTION_KICK_PLAYER = 0x03;
	public static final byte HOST_ACTION_CREATE_ROOM = 0x04;
	public static final byte HOST_ACTION_CLOSE_ROOM = 0x05;
	public static final byte HOST_ACTION_TIME_LIMIT = 0x06;
	public static final byte HOST_ACTION_SCORE_LIMIT = 0x07;
	public static final byte HOST_ACTION_CHANGE_GAMETYPE = 0x08;
	public static final byte HOST_ACTION_SET_BOUNDARIES = 0x09;
	public static final byte HOST_ACTION_BOUNDARY_UPDATES = 0x0A;
	public static final byte HOST_ACTION_CHANGE_HOST = 0x0B;
	public static final byte HOST_ACTION_ALLOW_VOTING = 0x0C;

	/*----------------------------------------------------------*/

	// Server IDs
	public static final byte PING = 0x02;
	public static final byte BROADCAST = 0x03;
	public static final byte TARGET = 0x04;
	public static final byte SPAWN_REGION = 0x05;
	public static final byte ABILITY_ACTION = 0x06;
	public static final byte GAME_START = 0x07;
	public static final byte GAME_END = 0x08;
	public static final byte ROOM_CLOSE = 0x09;
	public static final byte ROOM_KEY = 0x0A;
	public static final byte LOBBYINFO = 0x0B;
	public static final byte KICK = 0x0C;
	public static final byte NAK = 0x0D;
	public static final byte HOST = 0x0E;
	public static final byte CAUGHT = 0x0F;
	public static final byte JOIN_SUCCESS = 0x10;

	// Broadcast IDs
	public static final byte BROADCAST_TIME_REMAINING = 0x01;
	public static final byte BROADCAST_LEADERBOARD = 0x02;
	public static final byte BROADCAST_CAPTURE = 0x03;
	public static final byte BROADCAST_VOTES = 0x04;
	public static final byte BROADCAST_QUIT = 0x05;
	public static final byte BROADCAST_BOUNDARY_UPDATE = 0x06;
	public static final byte BROADCAST_NEW_HOST = 0x07;
	public static final byte BROADCAST_NEW_PLAYER = 0x08;
	public static final byte BROADCAST_PLAYER_READY = 0x09;

	// Lobby Information IDs
	public static final byte LOBBYINFO_GAMETYPE = 0x01;
	public static final byte LOBBYINFO_TIME_LIMIT = 0x02;
	public static final byte LOBBYINFO_SCORE_LIMIT = 0x03;
	public static final byte LOBBYINFO_BOUNDARIES = 0x04;
	public static final byte LOBBYINFO_LEADERBOARD = 0x05;
	public static final byte LOBBYINFO_ROOM_NAME = 0x06;
	public static final byte LOBBYINFO_VOTES = 0x07;
}