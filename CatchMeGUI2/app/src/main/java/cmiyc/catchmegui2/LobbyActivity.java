package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cmiyc.catchmegui2.game.UpdateLobbyInterface;
import cmiyc.catchmegui2.networking.packets.Packet;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyActivity extends AppCompatActivity implements UpdateLobbyInterface{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby);
        Home.player.setULInterface(this);





        Button leaveGameButton=(Button)findViewById(R.id.quitButton);
        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerLeave();
                Intent i = new Intent(LobbyActivity.this, Home.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

    public void playerLeave() {
        //Functionality to get rid of player from game
    }


    @Override
    public void ulScore(final int score) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView scoreLimit =(TextView)findViewById(R.id.scoreLimit);
                scoreLimit.setText(score);
            }
        });
    }

    @Override
    public void ulTime(final int time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView timeLimit = (TextView) findViewById(R.id.timeLimit);
                timeLimit.setText(time);
            }
        });
    }

    @Override
    public void ulType(final byte type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView gameMode =(TextView)findViewById(R.id.gameMode);
                switch (type){
                    case Packet.GAMETYPE_DEFAULT:
                        gameMode.setText("Individual");
                        break;
                    case Packet.GAMETYPE_MAN_HUNT:
                        gameMode.setText("Manhunt");
                        break;
                    case Packet.GAMETYPE_TEAM:
                        gameMode.setText("Team");
                        break;
                }
            }
        });
    }
}
