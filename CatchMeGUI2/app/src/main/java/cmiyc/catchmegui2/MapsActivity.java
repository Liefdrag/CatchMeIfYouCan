package cmiyc.catchmegui2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cmiyc.catchmegui2.game.room.MapsInterface;

/**
 * Class uses Google Maps. Code from this class is inspired by the code on the Google Developer website
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsInterface {

    private GoogleMap mMap;
    private Circle boundary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Home.player.setMInterface(this);
        setContentView(R.layout.map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
                finish();
            }
            public void onSwipeBottom() {
            }

        });
    }

    /**
     * Method that will update the Boundary
     * @param radius - New radius of the boundary
     */
    public void updateBoundary(int radius) {
        boundary.setRadius(radius);
    }

    /**
     * Method that creates the starting Boundary circle in the polygon
     * @param radius - Radius of the boundary
     * @param centerCoordinates - The center coordinates of the boundary
     */
    public void setBoundary(final int radius, final double[] centerCoordinates) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LatLng bPosition = new LatLng(centerCoordinates[0], centerCoordinates[1]);

                CircleOptions circleOptions = new CircleOptions()
                        .center(bPosition)
                        .radius(radius) // In meters
                        .strokeColor(Color.RED)
                        .fillColor(Color.TRANSPARENT); //Can change this at a later date

                boundary = mMap.addCircle(circleOptions);
            }});
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
            //double lat = Home.player.getGame().getRoom().getLobby().getBoundaryCentre()[1];
            //double lng = Home.player.getGame().getRoom().getLobby().getBoundaryCentre()[0];
           //LatLng boundaryCentre = new LatLng(lat, lng);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(51.22471, -2.19354);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Bath"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //Checks if the API is above 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Checks if the location permissions have been enabled
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true); //Allows maps to track location
            }
        }
        mMap.setMyLocationEnabled(true); //Allows maps to track location
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            //Gets the location
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng myPosition = new LatLng(latitude, longitude);
            //Makes the coordinate the user's location
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myPosition, 5);
            mMap.moveCamera(yourLocation);
            //Zooms into the map into the user's location
            CameraUpdate zoomIn = CameraUpdateFactory.zoomTo(14.0f);
            mMap.animateCamera(zoomIn);
            CircleOptions circleOptions = new CircleOptions()
                    .center(myPosition)
                    .radius(1000) // In meters
                    .strokeColor(Color.RED)
                    .fillColor(Color.TRANSPARENT); //Can change this at a later date

            boundary = mMap.addCircle(circleOptions);
        }
    }
}
