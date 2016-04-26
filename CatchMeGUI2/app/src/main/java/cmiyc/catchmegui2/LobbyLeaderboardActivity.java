package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyLeaderboardActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_leaderboard);

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
}
