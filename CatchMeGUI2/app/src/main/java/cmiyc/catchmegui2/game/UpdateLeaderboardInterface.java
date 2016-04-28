package cmiyc.catchmegui2.game;

/**
 * Created by Liefdrag on 28/04/2016.
 */
public interface UpdateLeaderboardInterface {

    public void buildLeaderboard();

    public void updateLeaderboard(int place, String name, int score);
}
