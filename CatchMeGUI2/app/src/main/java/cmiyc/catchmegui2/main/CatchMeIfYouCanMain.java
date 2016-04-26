package cmiyc.catchmegui2.main;
import java.net.UnknownHostException;

import cmiyc.catchmegui2.game.HostPlayer;
import cmiyc.catchmegui2.game.Player;


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
