package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import cmiyc.catchmegui2.game.UpdateLeaderboardInterface;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyLeaderboardActivity extends AppCompatActivity {

    public final TextView[] leaderboardArray = {
           (TextView) findViewById(R.id.leaderboardName1),
            (TextView) findViewById(R.id.leaderboardScore1),
            (TextView) findViewById(R.id.leaderboardName2),
            (TextView) findViewById(R.id.leaderboardScore2),
            (TextView) findViewById(R.id.leaderboardName3),
            (TextView) findViewById(R.id.leaderboardScore3),
            (TextView) findViewById(R.id.leaderboardName4),
            (TextView) findViewById(R.id.leaderboardScore4),
            (TextView) findViewById(R.id.leaderboardName5),
            (TextView) findViewById(R.id.leaderboardScore5),
            (TextView) findViewById(R.id.leaderboardName6),
            (TextView) findViewById(R.id.leaderboardScore6),
            (TextView) findViewById(R.id.leaderboardName7),
            (TextView) findViewById(R.id.leaderboardScore7),
            (TextView) findViewById(R.id.leaderboardName8),
            (TextView) findViewById(R.id.leaderboardScore8),
            (TextView) findViewById(R.id.leaderboardName9),
            (TextView) findViewById(R.id.leaderboardScore9),
            (TextView) findViewById(R.id.leaderboardName10),
            (TextView) findViewById(R.id.leaderboardScore10),
            (TextView) findViewById(R.id.leaderboardName11),
            (TextView) findViewById(R.id.leaderboardScore11),
            (TextView) findViewById(R.id.leaderboardName12),
            (TextView) findViewById(R.id.leaderboardScore12),
            (TextView) findViewById(R.id.leaderboardName13),
            (TextView) findViewById(R.id.leaderboardScore13),
            (TextView) findViewById(R.id.leaderboardName14),
            (TextView) findViewById(R.id.leaderboardScore14),
            (TextView) findViewById(R.id.leaderboardName15),
            (TextView) findViewById(R.id.leaderboardScore15),
            (TextView) findViewById(R.id.leaderboardName16),
            (TextView) findViewById(R.id.leaderboardScore16),
    };
    public static boolean created;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_leaderboard);

        if(!created) {
            buildLeaderboard();
            created = true;
        }

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(LobbyLeaderboardActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                finish();
            }
            public void onSwipeLeft() {
            }
            public void onSwipeBottom() {
            }

        });
    }

    private void buildLeaderboard() {
        for (int i = 0; i < leaderboardArray.length; i++) {
            leaderboardArray[i].setText("");
        }
    }

    public void updateLeaderboard(int place, String name, int score) {
        leaderboardArray[(place*2)-1].setText(name);
        leaderboardArray[(place*2)].setText(score);
    }
}
