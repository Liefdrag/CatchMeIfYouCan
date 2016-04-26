package cmiyc.catchmegui2.game;

import java.net.UnknownHostException;

import cmiyc.catchmegui2.networking.client.Client;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.*;


public class HostPlayer extends Player {

	public HostPlayer(String playerName) throws UnknownHostException {
		super(playerName);
	}
	
	public HostPlayer(String playerName, Client client, Game game){
		super(playerName, client, game);
	}

	@Override
	public void create(String roomName, double[] address) {
		CreateRoomPacket packet = new CreateRoomPacket();
		packet.putRoomName(roomName);
		packet.putHostName(playerName);
		packet.putMACAddress(address);
		getClient().sendPacket(packet);
	}

	public void close() {
		getClient().sendPacket(new CloseRoomPacket());
	}

	public void allowVoting() {
		AllowVotingPacket packet = new AllowVotingPacket();
		getClient().sendPacket(packet);
	}

	public void updateBoundaries(int interval, int percentage) {
		BoundaryUpdatesPacket packet = new BoundaryUpdatesPacket();
		packet.putBoundaryUpdates(interval, percentage);
		getClient().sendPacket(packet);
	}

	public void changeHost(int newHost) {
		ChangeHostPacket packet = new ChangeHostPacket();
		packet.putNewHostID(newHost);
	}

	public void endRound() {
		EndRoundPacket packet = new EndRoundPacket();
		getClient().sendPacket(packet);
	}

	public void setGametype(byte gametype) {
		GametypePacket packet = new GametypePacket();
		packet.putGameType(gametype);
		getClient().sendPacket(packet);
	}

	public void kick(int player) {
		KickPlayerPacket packet = new KickPlayerPacket();
		packet.putPlayerID(player);
		getClient().sendPacket(packet);
	}

	public void setScoreLimit(int scoreLimit) {
		ScoreLimitPacket packet = new ScoreLimitPacket();
		packet.putScoreLimit(scoreLimit);
		getClient().sendPacket(packet);
	}

	public void setBoundaries(double longitude, double latitude, int radius) {
		SetBoundariesPacket packet = new SetBoundariesPacket();
		packet.putBoundaries(longitude, latitude, radius);
		getClient().sendPacket(packet);
	}

	public void start() {
		StartRoundPacket packet = new StartRoundPacket();
		getClient().sendPacket(packet);
	}

	public void setTimeLimit(int timeLimit) {
		TimeLimitPacket packet = new TimeLimitPacket();
		packet.putTimeLimit(timeLimit);
		getClient().sendPacket(packet);
	}

}
