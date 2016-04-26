package cmiyc.catchmegui2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class GameLeaderboardActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_leaderboard);

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(GameLeaderboardActivity.this) {
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
