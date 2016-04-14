package game;

import java.net.UnknownHostException;

import networking.packets.clientPackets.hostPackets.*;

public class HostPlayer extends Player {

	public HostPlayer() throws UnknownHostException {
	}

	@Override
	public void create(String roomName, String hostName, double[] address) {
		CreateRoomPacket packet = new CreateRoomPacket();
		packet.putRoomName(roomName);
		packet.putHostName(hostName);
		packet.putMACAddress(address);
		client.sendPacket(packet);
	}

	public void close() {
		client.sendPacket(new CloseRoomPacket());
	}

	public void allowVoting() {
		AllowVotingPacket packet = new AllowVotingPacket();
		client.sendPacket(packet);
	}

	public void updateBoundaries(int interval, int percentage) {
		BoundaryUpdatesPacket packet = new BoundaryUpdatesPacket();
		packet.putBoundaryUpdates(interval, percentage);
		client.sendPacket(packet);
	}

	public void changeHost(int newHost) {
		ChangeHostPacket packet = new ChangeHostPacket();
		packet.putNewHostID(newHost);
	}

	public void endRound() {
		EndRoundPacket packet = new EndRoundPacket();
		client.sendPacket(packet);
	}

	public void setGametype(byte gametype) {
		GametypePacket packet = new GametypePacket();
		packet.putGameType(gametype);
		client.sendPacket(packet);
	}

	public void kick(int player) {
		KickPlayerPacket packet = new KickPlayerPacket();
		packet.putPlayerID(player);
		client.sendPacket(packet);
	}

	public void setScoreLimit(int scoreLimit) {
		ScoreLimitPacket packet = new ScoreLimitPacket();
		packet.putScoreLimit(scoreLimit);
		client.sendPacket(packet);
	}

	public void setBoundaries(double longitude, double latitude, int radius) {
		SetBoundariesPacket packet = new SetBoundariesPacket();
		packet.putBoundaries(longitude, latitude, radius);
		client.sendPacket(packet);
	}

	public void start() {
		StartRoundPacket packet = new StartRoundPacket();
		client.sendPacket(packet);
	}

	public void setTimeLimit(int timeLimit) {
		TimeLimitPacket packet = new TimeLimitPacket();
		packet.putTimeLimit(timeLimit);
		client.sendPacket(packet);
	}

}
