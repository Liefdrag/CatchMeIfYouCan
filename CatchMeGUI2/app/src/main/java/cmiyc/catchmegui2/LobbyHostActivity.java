package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyHostActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_host);

        TextView timeLimit =(TextView)findViewById(R.id.timeLimit);
        timeLimit.setText(""); //Set the Time Limit Here
        TextView scoreLimit =(TextView)findViewById(R.id.scoreLimit);
        scoreLimit.setText(""); //Set the Time Limit Here
        TextView gameMode =(TextView)findViewById(R.id.gameMode);
        gameMode.setText(""); //Set the Time Limit Here

        Button leaveGameHostButton=(Button)findViewById(R.id.quitHostButton);
        leaveGameHostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeHost();
                Intent i = new Intent(LobbyHostActivity.this, Home.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        Button startGameButton=(Button)findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
                Intent i = new Intent(LobbyHostActivity.this, InGameActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        Button backButton=(Button)findViewById(R.id.backHostLobbyButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
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

    public void changeHost() {
        Home.player.quit();
        //functionality to change the host
    }

    public void startGame() {
        //Functionality to Start the Game
    }
}
