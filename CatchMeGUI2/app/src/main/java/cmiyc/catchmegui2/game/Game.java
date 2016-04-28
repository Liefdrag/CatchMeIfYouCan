package cmiyc.catchmegui2.game;


import cmiyc.catchmegui2.game.room.Leaderboard;
import cmiyc.catchmegui2.game.room.Room;

/**
 * This class contains methods that are called when packets are received.
 *
 */
public class Game {

	public enum GameState {
		LOBBY, GAME, END;
	}

	private final String playerName;
	private int targetID;
	private Room room;
	private GameState gameState;
	private Leaderboard leaderboard;
    private UpdateLobbyInterface uli;

	public Game(String playerName, String roomKey, boolean host) {
		this.playerName = playerName;
		room = new Room(roomKey, host);
		leaderboard = new Leaderboard();
	}
	
	public void updateLeaderboard(Leaderboard newLeaderboard) {
		leaderboard = newLeaderboard;
		//Refresh GUI Leaderboard
	}

	public void addPlayer(String playerName) throws Exception {
		room.addPlayer(playerName);
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

	public void playerCaught(String playerName) {
		if (this.playerName == playerName) {
			// Alert the client they've been caught
		}
		// Alert the client player has been caught
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
                System.out.println("updating time limit");
				room.getLobby().setTimeLimit((int) data);
                for(int i = 0; i < 10; i++){
                    if (uli != null){
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

	public void startGame() {
		gameState = GameState.GAME;
	}

	public void endGame() {
		gameState = GameState.END;
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
	
	public int getPlayerID(String playerName) {
		// this needs to get from lb
		return 0;
	}

    public void setUIInterface(UpdateLobbyInterface uli){
        this.uli = uli;
    }
}
