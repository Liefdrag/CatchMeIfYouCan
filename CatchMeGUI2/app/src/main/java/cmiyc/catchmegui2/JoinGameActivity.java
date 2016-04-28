package cmiyc.catchmegui2;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmiyc.catchmegui2.game.JoinSuccessInterface;

/**
 * Created by Liefdrag on 12/04/2016.
 */
public class JoinGameActivity extends AppCompatActivity implements JoinSuccessInterface{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_game);
        Home.player.setJSInterface(this);
        Button Button1 = (Button)findViewById(R.id.joinGameLobbyButton);
        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterRoom();

            }
        });
        Button Button2 = (Button)findViewById(R.id.backjoinGameButton);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(JoinGameActivity.this, Home.class);
                finish();
            }
        });
    }

    public void enterRoom() {
        Button Button1 = (Button)findViewById(R.id.joinGameLobbyButton);
        EditText roomKey = (EditText)findViewById(R.id.enterKey);
        String key = roomKey.getText().toString();
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        Home.player.create(key, info.getMacAddress());
        Button1.setEnabled(false);
    }

    @Override
    public void joinSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(JoinGameActivity.this, VotingActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void joinFailure(final String reason) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
              switch (reason) {
                  case "KEY":
                      //invalid key
                      break;
                  case "FULL":
                      //game full
                      break;
                  case "INGAME":
                      //room already in an active game
                      break;
                  default:
                      break;
              }
              Button Button1 = (Button)findViewById(R.id.joinGameLobbyButton);
              Button1.setEnabled(true);
          }
      });

        //Invalid roomKey/Room full/In game notification
    }
}
