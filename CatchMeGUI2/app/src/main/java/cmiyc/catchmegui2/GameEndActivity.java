package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liefdrag on 27/04/2016.
 */
public class GameEndActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_end);

        TextView winner =(TextView)findViewById(R.id.winningPlayer);
        winner.setText("Steve"); //Set the winning player here
        TextView position =(TextView)findViewById(R.id.playerPosition);
        position.setText("2/6"); //Set the player position here
        TextView score =(TextView)findViewById(R.id.playerScore);
        score.setText("1000"); //Set the player score here
        TextView captures =(TextView)findViewById(R.id.playerCaptures);
        captures.setText("1 time"); //Set the player captures here
        TextView caughts =(TextView)findViewById(R.id.playerCaughts);
        caughts.setText("5 times"); //Set the player caughts here


        Button lobbyButton =(Button)findViewById(R.id.reenterLobbyButton);
        lobbyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //NEEDS TO CHECK IF HOST
                Intent i = new Intent(GameEndActivity.this, VotingActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        Button leaveGameButton = (Button)findViewById(R.id.leaveEndGameButton);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //NEEDS TO CHECK IF HOST
                playerLeave();
                Intent i = new Intent(GameEndActivity.this, Home.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(GameEndActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
                Intent i = new Intent(GameEndActivity.this, GameLeaderboardActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }

    public void playerLeave() {
        //Functionality to get rid of player from lobby
    }

}
