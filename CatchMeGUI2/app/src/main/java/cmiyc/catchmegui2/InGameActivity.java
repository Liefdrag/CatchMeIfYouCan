package cmiyc.catchmegui2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;

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

        Button caughtButton = (Button)findViewById(R.id.caughtButton);
        caughtButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                caughtButtonClicked();
            }
        });

        Button beencaughtButton = (Button)findViewById(R.id.beenCaughtButton);
        beencaughtButton.setEnabled(false);
        beencaughtButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beenCaughtButtonClicked();
            }
        });

        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(InGameActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                Intent i = new Intent(InGameActivity.this, MapsActivity.class);
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

    public void changeBeenCaughtButton(boolean state) {
        Button beencaughtButton = (Button)findViewById(R.id.beenCaughtButton);
        beencaughtButton.setEnabled(state);
    }

    public void setPlayerName(String name) {
        TextView userName = (TextView)findViewById(R.id.playerName);
        userName.setText(name);
    }

    public void setPlayerScore(int score) {
        TextView playerScore = (TextView)findViewById(R.id.playerScore);
        playerScore.setText(score);
    }

    public void caughtButtonClicked() {
        //Sends Packet To Server
    }

    public void beenCaughtButtonClicked() {
        //Sends Packet To Server
    }

    public void setTimer(int time) {
        TextView timer = (TextView)findViewById(R.id.gameTimer);
        timer.setText(time);
    }

    /**
     * Method that returns the player's location
     * @return - Array of doubles where first element is latitude and second longitude
     */
    public double[] getPlayerLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Checks if the location permissions have been enabled
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            }
        }
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        if(location != null) {
            //Gets the location
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            double[] locationCoordinates = {latitude, longitude};
            return locationCoordinates;
        }
        return null;
    }
}
