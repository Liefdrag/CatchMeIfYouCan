package networking.packets;

import networking.packets.Packet;
import networking.packets.clientPackets.hostPackets.*;

public class PacketConstructor {

	public Packet constructAllowVotingPacket() {
		AllowVotingPacket packet = new AllowVotingPacket();
		return packet;
	}

	public Packet constructBoundaryUpdatesPacket(int interval, int percentage) {
		BoundaryUpdatesPacket packet = new BoundaryUpdatesPacket();
		packet.putBoundaryUpdates(interval, percentage);
		return packet;
	}

	public Packet constructChangeHostPacket(int hostID) {
		ChangeHostPacket packet = new ChangeHostPacket();
		packet.putNewHostID(hostID);
		return packet;
	}

	public Packet constructCloseRoomPacket() {
		CloseRoomPacket packet = new CloseRoomPacket();
		return packet;
	}

	public Packet constructCreateRoomPacket(String roomName, String hostName,
			double[] address) {
		CreateRoomPacket packet = new CreateRoomPacket();
		packet.putRoomName(roomName);
		packet.putHostName(hostName);
		packet.putMACAddress(address);
		return packet;
	}
	
	public Packet constructEndRoundPacket() {
		EndRoundPacket packet = new EndRoundPacket();
		return packet;
	}
	
	public Packet constructGametypePacket(byte gameType) {
		GametypePacket packet = new GametypePacket();
		packet.putGameType(gameType);
		return packet;
	}
	
	public Packet constructKickPlayerPacket(int playerID) {
		KickPlayerPacket packet = new KickPlayerPacket();
		packet.putPlayerID(playerID);
		return packet;
	}
	
	public Packet constructScoreLimitPacket(int score) {
		ScoreLimitPacket packet = new ScoreLimitPacket();
		packet.putScoreLimit(score);
		return packet;
	}
	
	public Packet constructSetBoundariesPacket(double longitude, double latitude, int radius) {
		SetBoundariesPacket packet = new SetBoundariesPacket();
		packet.putBoundaries(longitude, latitude, radius);
		return packet;
	}
	
	public Packet constructStartRoundPacket() {
		StartRoundPacket packet = new StartRoundPacket();
		return packet;
	}
	
	public Packet constructTimeLimitPacket(int time) {
		TimeLimitPacket packet = new TimeLimitPacket();
		packet.putTimeLimit(time);
		return packet;
	}
}
