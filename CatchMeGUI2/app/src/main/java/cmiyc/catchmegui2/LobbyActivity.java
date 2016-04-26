package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby);

        TextView timeLimit =(TextView)findViewById(R.id.timeLimit);
        timeLimit.setText("1:00"); //Set the Time Limit Here
        TextView scoreLimit =(TextView)findViewById(R.id.scoreLimit);
        scoreLimit.setText("1000"); //Set the Time Limit Here
        TextView gameMode =(TextView)findViewById(R.id.gameMode);
        gameMode.setText("Manhunt"); //Set the Time Limit Here


        Button leaveGameButton=(Button)findViewById(R.id.quitButton);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LobbyActivity.this, Home.class);
                startActivity(i);

            }
        });

        Button invitePlayerButton = (Button)findViewById(R.id.friendInviteLobbyButton);
        invitePlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LobbyActivity.this, InvitePlayerActivity.class);
                startActivity(i);
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(LobbyActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
                Intent i = new Intent(LobbyActivity.this, LobbyLeaderboardActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }


}
