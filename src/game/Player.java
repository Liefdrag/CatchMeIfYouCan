package game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import networking.client.Client;
import networking.packets.clientPackets.*;

/**
 * Client -> Server methods
 */
public class Player {
	
	protected Client client;
	protected Game game;
	protected String playerName;
	
	public Player(String playerName) throws UnknownHostException{
		client = new Client(InetAddress.getLocalHost().toString(), 0);
		game = null;
		this.playerName = playerName;
	}	
	
	public void create(String roomKey, double[] address){
		JoinPacket packet = new JoinPacket();
		packet.putRoomKey(roomKey);
		packet.putPlayerName(playerName);
		packet.putMACAddress(address);
		client.sendPacket(packet);
	}
	
	public void useAbility(byte ability) {
		AbilityUsagePacket packet = new AbilityUsagePacket();
		packet.putAbility(ability);
		client.sendPacket(packet);
	}
	
	public void captureTarget() {
		CatchPerformedPacket packet = new CatchPerformedPacket();
		client.sendPacket(packet);
	}
	
	public void playerCaptured() {
		CapturedPacket packet = new CapturedPacket();
		client.sendPacket(packet);
	}
	
	public void ready() {
		PlayerReadyPacket packet = new PlayerReadyPacket();
		client.sendPacket(packet);
	}
	
	public void quit() {
		QuitPacket packet = new QuitPacket();
		client.sendPacket(packet);
	}
	
	public void vote(byte vote) {
		VotePacket packet = new VotePacket();
		packet.putVote(vote);
		client.sendPacket(packet);
	}
	
	public void report(int player){
		ReportPacket packet = new ReportPacket();
		packet.report(player);
		client.sendPacket(packet);
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	
	public String getPlayerName(){
		return playerName;
	}
}
