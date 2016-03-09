package game.room;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private String roomKey;
	private List<Integer> players;
	private State roomState;
	private boolean isHost;
	private Leaderboard leaderboard;
	
	private enum State {
		GAME, LOBBY, STARTING, ENDING, PAUSED, FINISHED
	}
	
	public Room() {
		this(false);
	}
	
	public Room(boolean isHost) {
		players = new ArrayList<Integer>();
		roomState = State.LOBBY;
		this.isHost = isHost;
		this.leaderboard = new Leaderboard();
	}

	public String getRoomKey() {
		return roomKey;
	}

	public void setRoomKey(String roomKey) {
		this.roomKey = roomKey;
	}

	public List<Integer> getPlayers() {
		return players;
	}

	public void addPlayer(Integer playerID) {
		players.add(playerID);
	}
	
	public void removePlayer(Integer playerID) {
		players.remove(playerID);
	}

	public State getRoomState() {
		return roomState;
	}

	public void setRoomState(State roomState) {
		this.roomState = roomState;
	}
	
	public Boolean getIsHost() {
		return isHost;
	}

	public void setIsHost(Boolean isHost) {
		this.isHost = isHost;
	}

	public Leaderboard getLeaderboard() {
		return leaderboard;
	}

	public void setLeaderboard(Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
	}
}
