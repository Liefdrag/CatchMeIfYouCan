package cmiyc.catchmegui2.game.room;

import java.util.List;

public class Room {

	private final Lobby lobby;
	private final String roomKey;
	private List<String> players;
	private boolean host;

	public Room(String roomKey, boolean host) {
		this.lobby = new Lobby();
		this.roomKey = roomKey;
		this.setHost(host);
	}

	public void addPlayer(String playerName) {
		players.add(playerName);
	}

	public void removePlayer(String playerName) {
		players.remove(playerName);
	}

	public String getRoomKey() {
		return roomKey;
	}

	public List<String> getPlayers() {
		return players;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public Lobby getLobby() {
		return lobby;
	}

	public final class Lobby {

		private byte gametype;
		private String RoomName;
		private int scoreLimit;
		private int timeLimit;

		private double[] boundaryCentre;
		private double boundaryRadius;

		private Lobby() {
		}

		public byte getGametype() {
			return gametype;
		}

		public void setGametype(byte gametype) {
			this.gametype = gametype;
		}

		public String getRoomName() {
			return RoomName;
		}

		public void setRoomName(String roomName) {
			RoomName = roomName;
		}

		public int getScoreLimit() {
			return scoreLimit;
		}

		public void setScoreLimit(int scoreLimit) {
			this.scoreLimit = scoreLimit;
		}

		public int getTimeLimit() {
			return timeLimit;
		}

		public void setTimeLimit(int timeLimit) {
			this.timeLimit = timeLimit;
		}

		public double[] getBoundaryCentre() { return boundaryCentre; }

		public void setBoundaryCentre(double[] boundaryCentre) { this.boundaryCentre = boundaryCentre; }

		public double getBoundaryRadius() { return boundaryRadius; }

		public void setBoundaryRadius(double boundaryRadius) { this.boundaryRadius = boundaryRadius; }
	}
}
