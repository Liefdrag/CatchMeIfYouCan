package cmiyc.catchmegui2.game;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import java.util.Timer;
import java.util.TimerTask;

import cmiyc.catchmegui2.Home;
import cmiyc.catchmegui2.game.room.Leaderboard;
import cmiyc.catchmegui2.game.room.Room;
import cmiyc.catchmegui2.networking.packets.LocationPacket;

/**
 * This class contains methods that are called when packets are received.
 *
 */
public class LocationTimerTask extends TimerTask {

    public final Context context;

    public LocationTimerTask(Context c){
        this.context = c;
    }

    /**
     * Method is called when the TimerTask's timer has reached its time
     */
    public void run() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Checks if the location permissions have been enabled
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(provider);
                double latitude = 0;
                double longitude = 0;
                if(location!=null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
                LocationPacket lp = new LocationPacket();
                lp.putLocation(longitude, latitude);
                Home.player.sendPacket(lp);
            }
        } else {
            LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            double latitude = 0;
            double longitude = 0;
            if(location!=null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            LocationPacket lp = new LocationPacket();
            lp.putLocation(longitude, latitude);
            Home.player.sendPacket(lp);
        }
        Home.player.updateCompass();
    }
}
