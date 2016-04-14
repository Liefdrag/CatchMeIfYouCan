package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 12/04/2016.
 */
public class InGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);

        Button gameOptionsButton = (Button)findViewById(R.id.gameOptionsButton);
        gameOptionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(InGameActivity.this, InGameOptionsActivity.class);
                startActivity(i);
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(InGameActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                Intent i = new Intent(InGameActivity.this, MapActivity.class);
                startActivity(i);
            }
            public void onSwipeLeft() {
                Intent i = new Intent(InGameActivity.this, GameLeaderboardActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }
}
