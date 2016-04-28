package cmiyc.catchmegui2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Liefdrag on 12/04/2016.
 */
public class InGameActivity extends AppCompatActivity {

    AlertDialog caughtDialog;
    AlertDialog catchDialog;
    private final Button[] compassPoints = {
            (Button)findViewById(R.id.topMiddle),
            (Button)findViewById(R.id.topRight),
            (Button)findViewById( R.id.middleRight),
            (Button)findViewById(R.id.bottomRight),
            (Button)findViewById(R.id.bottomMiddle),
            (Button)findViewById(R.id.bottomLeft),
            (Button)findViewById(R.id.middleLeft),
            (Button)findViewById(R.id.topLeft),
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);

        if(PlayerDetailsActivity.avatarPhoto != null) {
            ImageView avatar = (ImageView)findViewById(R.id.imageView1);
            avatar.setImageURI(null);
            avatar.setImageURI(PlayerDetailsActivity.avatarPhoto);
        }
        else {
            ImageView avatar = (ImageView)findViewById(R.id.imageView1);
            Drawable iconDrawable = getResources().getDrawable(R.drawable.ic_launcher);
            avatar.setImageDrawable(iconDrawable);
        }

        AlertDialog.Builder catchBuilder = new AlertDialog.Builder(this);
        catchBuilder.setMessage("Catch Success")
                .setCancelable(false)
                .setPositiveButton("OK :D", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Do Nothing
                    }
                });
        catchDialog = catchBuilder.create();

        AlertDialog.Builder caughtBuilder = new AlertDialog.Builder(this);
        caughtBuilder.setMessage("You have been unfortunately caught")
                .setCancelable(false)
                .setPositiveButton("OK :(", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Do Nothing
                    }
                });
        caughtDialog = caughtBuilder.create();

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
        //beencaughtButton.setEnabled(false);
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
        catchDialog.show();
        //Sends Packet To Server
    }

    public void beenCaughtButtonClicked() {
        caughtDialog.show();
        //Sends Packet To Server
    }

    /**
     * Method that changes the compass buttons
     * @param direction - integer that corresponds to direction, 0 for north, 1 for north-east, 2 for east
     */
    public void changeCompass(int direction, double distance) {
        //distance - CAN BE USED FOR CHANGING THE COLOUR OF THE COMPASS
        for (int i = 0; i < 8; i++) {
            if (i == direction) {
                compassPoints[i].getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
            }
            else {
                compassPoints[i].getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    /**
     * Works out the bearing and distance
     * @param coordinates- Coordinates of their target. Position 0 - Latitude, Position 1 - Longitude
     */
    public void compassCalibration(double[] coordinates) {
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
        double latitude = 0;
        double longitude = 0;
        if(location!=null) {
            //Gets the location
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        /////// CALCULATES THE BEARING ////////
        double x = Math.cos(coordinates[0]) * Math.sin(Math.abs(coordinates[1]- longitude));
        double y = Math.cos(latitude) * (Math.sin(coordinates[0]) - Math.sin(latitude)) * Math.cos(coordinates[0]) * Math.cos(Math.abs(coordinates[1]- longitude));

        double bearing = Math.atan2(x,y)  ;
        ////// CALCULATES THE DISTANCE ////////
        double distance = Math.sqrt(Math.pow(latitude - coordinates[0],2) + Math.pow(longitude - coordinates[1],2));
        ////// CHANGES THE COMPASS TO FIT THESE PARAMETERS
        changeCompass(calculateDirectionInt(bearing), distance); //Method changes the compass
    }

    /**
     * Method returns the direction on the compass as an integer
     * @param bearing - Degree bearing the target is from the player
     * @return - Integer direction of the target which will be used for the compass
     */
    private int calculateDirectionInt(double bearing) {

        if ((337.5 <= bearing) || (bearing < 22.5)) {
            return 0; //North
        }
        else if ((22.5 <= bearing) && (bearing < 67.5)) {
            return 1; //North-East
        }
        else if ((67.5 <= bearing)&& (bearing < 112.5)) {
            return 2; //East
        }
        else if ((112.5 <= bearing)&& (bearing < 157.5)) {
            return 3; //South-East
        }
        else if (( 157.5 <= bearing)&& (bearing < 202.5)) {
            return 4; //South
        }
        else if (( 202.5 <= bearing)&& (bearing < 247.5)) {
            return 5; //South-West
        }
        else if (( 247.5 <= bearing)&& (bearing < 292.5)) {
            return 6; //West
        }
        else if (( 292.5 <= bearing) && (bearing < 337.5)) {
            return 7; //North-West
        }
        else {
            return 0; //Shouldnt ever get here
        }


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

