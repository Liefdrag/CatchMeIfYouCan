package game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import networking.client.Client;

/**
 * Client -> Server methods
 */
public class Player {
	
	protected Client client;
	protected Game game;
	
	public Player() throws UnknownHostException{
		client = new Client(InetAddress.getLocalHost().toString(), 0);
		game = null;
	}	
	
	public void useAbility(byte[] ability) {
	}
	
	public void captureTarget() {
	}
	
	public void playerCaptured() {
	}
	
	public void join(String roomKey) {
	}
	
	public void ready() {
	}
	
	public void quit() {
	}
	
	public void vote(byte[] data) {
	}
	
}
