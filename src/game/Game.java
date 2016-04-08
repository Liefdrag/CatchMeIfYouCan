package game;

import game.room.Leaderboard;
import game.room.Room;

public class Game {

	public enum GameState {
		LOBBY, GAME, END;
	}

	private final Integer playerID;
	private Integer targetID;
	private Room room;
	private GameState gameState;
	private Leaderboard leaderboard;

	public Game(Integer playerID) {
		this.playerID = playerID;
		leaderboard = new Leaderboard();
	}
	
	public void updateLeaderboard(Leaderboard newLeaderboard) {
		leaderboard.updateLeaderboard(newLeaderboard);
		//Refresh GUI Leaderboard
	}

	public void joinRoom(String roomKey) {
		room = new Room(roomKey, false);
		gameState = GameState.LOBBY;
		// Send JoinPacket
	}

	public void createRoom() {
		String roomKey = null; // Get from Server
		room = new Room(roomKey, true);
		gameState = GameState.LOBBY;
		// Send CreateRoomPacket
	}

	public void addPlayer(Integer playerID) throws Exception {
		room.addPlayer(playerID);
	}

	public void removePlayer(Integer playerID) throws Exception {
		if (!room.getPlayers().contains(playerID)) {
			throw new Exception("Player does not exist.");
		}
		room.removePlayer(playerID);
	}

	public void setHost() {
		room.setHost(true);
	}

	public void playerCaught(Integer playerID) {
		if (this.playerID == playerID) {
			// Alert the client they've been caught
		}
		// Alert the client player has been caught
	}

	public void setTarget(Integer targetID) {
		this.targetID = targetID;
	}

	public void updateLobbyInfo(String toUpdate, Object data) {
		switch (toUpdate) {
			case "ROOM_NAME":
				room.getLobby().setRoomName((String) data);
				break;
			case "GAME_TYPE":
				room.getLobby().setGametype((String) data);
				break;
			case "SCORE_LIMIT":
				room.getLobby().setScoreLimit((int) data);
				break;
			case "TIME_LIMIT":
				room.getLobby().setTimeLimit((int) data);
				break;
			default:
				break;
		}
	}

	public void setRoomName(String roomName) {
		room.getLobby().setRoomName(roomName);
		// Send roomname packet?
	}

	public void setGametype(String gametype) {
		room.getLobby().setGametype(gametype);
		// Send gametype Packet
	}

	public void setScoreLimit(int scoreLimit) {
		room.getLobby().setScoreLimit(scoreLimit);
		// Send scorelimit packet
	}

	public void setTimeLimit(int timeLimit) {
		room.getLobby().setTimeLimit(timeLimit);
		// Send timelimit packet
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
}
