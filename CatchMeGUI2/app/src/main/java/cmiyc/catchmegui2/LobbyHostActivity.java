package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyHostActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_host);
        Button leaveGameHostButton=(Button)findViewById(R.id.quitHostButton);
        leaveGameHostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LobbyHostActivity.this, Home.class);
                startActivity(i);
            }
        });
        Button startGameButton=(Button)findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LobbyHostActivity.this, InGameActivity.class);
                startActivity(i);
            }
        });
        Button backButton=(Button)findViewById(R.id.backHostLobbyButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LobbyHostActivity.this, CreateGameActivity.class);
                startActivity(i);
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(LobbyHostActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
                Intent i = new Intent(LobbyHostActivity.this, LobbyLeaderboardActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }
}
