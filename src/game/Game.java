package game;

import game.room.Room;

public class Game {

	public enum GameState {
		LOBBY, GAME, END;
	}
	
	private final Integer playerID;
	private Integer targetID;
	private Room room;
	private GameState gameState;

	public Game(Integer playerID) {
		this.playerID = playerID;
	}

	public void joinRoom(String roomKey) {
		room = new Room(roomKey, false);
		gameState = GameState.LOBBY;
	}

	public void createRoom() {
		String roomKey = null; // Get from Server
		room = new Room(roomKey, true);
		gameState = GameState.LOBBY;
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
	
	public void setTarget(Integer targetID){
		this.targetID = targetID;
	}

	public void setRoomName(String roomName) {
		room.getLobby().setRoomName(roomName);
	}

	public void setGametype(String gametype) {
		room.getLobby().setGametype(gametype);
	}

	public void setScoreLimit(int scoreLimit) {
		room.getLobby().setScoreLimit(scoreLimit);
	}

	public void setTimeLimit(int timeLimit) {
		room.getLobby().setTimeLimit(timeLimit);
	}
	
	public void startGame(){
		gameState = GameState.GAME;
	}
	
	public void endGame(){
		gameState = GameState.END;
		closeRoom();
	}
	
	public void closeRoom(){
		room = null;
	}
	
	public GameState getGameState(){
		return gameState;
	}
}
