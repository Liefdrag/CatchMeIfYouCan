package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmiyc.catchmegui2.game.HostPlayer;
import cmiyc.catchmegui2.game.UpdateLobbyInterface;
import cmiyc.catchmegui2.networking.packets.Packet;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class LobbyHostActivity extends AppCompatActivity implements UpdateLobbyInterface {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_host);
        Home.player.setULInterface(this);
        TextView timeLimit = (TextView) findViewById(R.id.timeLimit);
        String time = Integer.toString(Home.player.getGame().getRoom().getLobby().getTimeLimit()) + " seconds";
        timeLimit.setText(time);
        TextView scoreLimit =(TextView)findViewById(R.id.scoreLimit);
        String score = Integer.toString(Home.player.getGame().getRoom().getLobby().getScoreLimit()) + " points";
        scoreLimit.setText(score);
        TextView gameMode =(TextView)findViewById(R.id.gameMode);
        switch (Home.player.getGame().getRoom().getLobby().getGametype()){
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
        ((HostPlayer)Home.player).start();
        //Functionality to Start the Game
    }

    @Override
    public void ulScore(final int score) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView scoreLimit =(TextView)findViewById(R.id.scoreLimit);
                scoreLimit.setText(score + " points");
                System.out.println("Score limit: "+score);
            }
        });
    }

    @Override
    public void ulTime(final int time) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView timeLimit = (TextView) findViewById(R.id.timeLimit);
                timeLimit.setText(time + " seconds");
            }
        });
    }

    @Override
    public void ulType(final byte type) {
        this.runOnUiThread(new Runnable() {
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
