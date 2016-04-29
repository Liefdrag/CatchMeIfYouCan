package cmiyc.catchmegui2.game;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import java.util.Timer;
import java.util.TimerTask;

import cmiyc.catchmegui2.Home;
import cmiyc.catchmegui2.game.room.Leaderboard;
import cmiyc.catchmegui2.game.room.Room;
import cmiyc.catchmegui2.networking.packets.LocationPacket;

/**
 * This class contains methods that are called when packets are received.
 *
 */
public class Game {

	public enum GameState {
		LOBBY, GAME, END;
	}

	public int playerID;

	private final String playerName;
    private LocationTimerTask ltt;
	private int targetID;
    private double[] targetLocation = new double[]{1000, 1000};
	private Room room;
	private GameState gameState;
	private Leaderboard leaderboard;
    private UpdateLobbyInterface uli;
    private Timer gameTimer;

	public Game(String playerName, String roomKey, boolean host) {
		this.playerName = playerName;
		room = new Room(roomKey, host);
        if (room == null){
            System.out.println("room is null");
        }
		leaderboard = new Leaderboard();
	}

	public void updateLeaderboard(Leaderboard newLeaderboard) {
		leaderboard = newLeaderboard;
        //LobbyLeaderboardActivity.created = false;

		//Refresh GUI Leaderboard
	}

	public void addPlayer(String playerName) throws Exception {
        if (room != null){
            room.addPlayer(playerName);
        } else{
            System.out.println("room is null");
        }

	}

	public void removePlayer(String playerName, byte kickReason) throws Exception {
		if (!room.getPlayers().contains(playerName)) {
			throw new Exception("Player does not exist.");
		}
		room.removePlayer(playerName);
	}

	public void setHost() {
		room.setHost(true);
	}

    public void catchPerformed(){
        targetLocation = new double[]{1000, 1000};
    }

	public void playerCaught(String playerName) {
        targetLocation = new double[]{1000, 1000};

	}

	public void setTarget(int[] targetID) {
		this.targetID = targetID[0];
	}

	public void updateLobbyInfo(String toUpdate, Object data) {
		switch (toUpdate) {
			case "ROOM_NAME":
				room.getLobby().setRoomName((String) data);
				break;
			case "GAME_TYPE":
                System.out.println("updating gametype");
				room.getLobby().setGametype((byte) data);
                for(int i = 0; i < 10; i++){
                    if (uli != null){
                        uli.ulType((byte) data);
                        break;
                    } else {
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
				break;
			case "SCORE_LIMIT":
                System.out.println("updating score limit");
				room.getLobby().setScoreLimit((int) data);
                for(int i = 0; i < 10; i++){
                    if (uli != null){
                        System.out.println("Score recieved: "+(int) data);
                        uli.ulScore((int) data);
                        break;
                    } else {
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
				break;
			case "TIME_LIMIT":

				room.getLobby().setTimeLimit((int) data);
                for(int i = 0; i < 10; i++){
                    if (uli != null){
                        System.out.println("updating time limit");
                        uli.ulTime((int) data);
                        break;
                    } else {
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
				break;
			default:
				break;
		}
	}

	public void updateBoundary(double[] centre, int radius) {
		room.getLobby().setBoundaryCentre(centre);
		room.getLobby().setBoundaryRadius(radius);
        Home.player.updateMapBoundary(centre, radius);
	}

	public void startGame() {
		gameState = GameState.GAME;
	}

    public void startTimer(){
        gameTimer = new Timer();
        //Sets off the timer after 10 seconds, perhaps set to 30
        gameTimer.schedule(ltt, (long) 10000, (long) 10);
    }

	public void endGame() {
		gameState = GameState.END;
        gameTimer.cancel();
		closeRoom();
	}

	public void closeRoom() {
		room = null;
	}

	public GameState getGameState() {
		return gameState;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerNameFromID(int playerID) {
		return leaderboard.getPlayerNameFromID(playerID);
	}

	public void setUIInterface(UpdateLobbyInterface uli){
    this.uli = uli;
  }

    public void setID(int id){
        playerID = id;
    }

    public Room getRoom(){
        return room;
    }

    public void setTargetLocation(double[] location){
        targetLocation = location;
    }

    public double[] getTargetLocation(){
        return targetLocation;
    }

    public void setltt(LocationTimerTask ltt){
        this.ltt = ltt;
    }

    public int getTargetID(){
        return targetID;
    }
}
