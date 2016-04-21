package main;
import java.net.UnknownHostException;

import game.HostPlayer;
import game.Player;
import networking.client.Client;
import networking.packets.Packet;

public class CatchMeIfYouCanMain {

	public static Player test;
	
	public static void main(String[] args) {
		try {
			test = new HostPlayer("Player 1");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
