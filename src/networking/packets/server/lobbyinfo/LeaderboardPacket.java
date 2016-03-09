package networking.packets.server.lobbyinfo;

import networking.packets.ServerPacket;

public class LeaderboardPacket extends ServerPacket {
	
	public LeaderboardPacket() {
		putByte(BROADCAST);
		putByte(BROADCAST_LEADERBOARD);
	}

	public LeaderboardPacket(byte[] data) {
		packet = data;
	}

	public void putLeaderboard(Leaderboard board) { // will require a
													// Leaderboard class.
		int size = board.getSize();
		putInt(size);// number of players in the board
		for (int i = 0; i < size; i++) {
			putInt(board.getPlayerID(i));
			putString(board.getPlayerName(i));
			putInt(board.getPlayerScore(i));
			putInt(board.getPlayerTeam(i));
		}
	}

	public Leaderboard getLeaderboard() {
		int size = getInt();
		Leaderboard board = new Leaderboard();
		for (int i = 0; i < size; i++) {
			board.addExistingPlayer(getInt(), getString(), getInt(), getInt());
		}
		return board;
	}

}