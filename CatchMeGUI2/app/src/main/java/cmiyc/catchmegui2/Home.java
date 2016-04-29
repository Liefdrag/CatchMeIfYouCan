package cmiyc.catchmegui2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import cmiyc.catchmegui2.game.Game;
import cmiyc.catchmegui2.game.HostPlayer;
import cmiyc.catchmegui2.game.Player;
import cmiyc.catchmegui2.game.UpdateLeaderboardInterface;
import cmiyc.catchmegui2.networking.client.Client;
import cmiyc.catchmegui2.packetParsers.PacketParser;
import cmiyc.catchmegui2.game.LocationTimerTask;

public class Home extends AppCompatActivity {

    public static Player player;
    public static PacketParser pcktparser = new PacketParser();
    private Context context = new PlayerDetailsActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button Button1=(Button)findViewById(R.id.createGameButton);
        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.player_name), Context.MODE_PRIVATE);
                String defaultName = "Player";
                String playerName = sharedPref.getString(getString(R.string.saved_name), defaultName);*/
                player = new HostPlayer("Tucker", new Client("138.38.247.244", 10401, pcktparser), null);
                WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = manager.getConnectionInfo();
                String address = info.getMacAddress();
                ((HostPlayer) player).create("Test Room", address);
                //LobbyLeaderboardActivity.created = false;
                Intent i = new Intent(Home.this, CreateGameActivity.class);
                startActivity(i);
            }
        });
        Button Button2=(Button)findViewById(R.id.joinGameButton);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.player_name), Context.MODE_PRIVATE);
                String defaultName = "Player";
                String playerName = sharedPref.getString(getString(R.string.saved_name), defaultName);*/
                player = new Player("Caboose", new Client("138.38.247.244", 10401, pcktparser), null);
                Intent i = new Intent(Home.this, JoinGameActivity.class);
                startActivity(i);
            }
        });
        Button Button3=(Button)findViewById(R.id.playerDetailsButton);
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Home.this, PlayerDetailsActivity.class);
                startActivity(i);
            }
        });
        Button Button4=(Button)findViewById(R.id.howToPlayButton);
        Button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Home.this, HowToPlayActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
