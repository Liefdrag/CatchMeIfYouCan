package cmiyc.catchmegui2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Liefdrag on 27/04/2016.
 */
public class SplashActivity extends Activity {
    private static boolean loadSplash = true; //Boolean to determine whether Splash Screen has appeared

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Will not show splash again in the lifecycle
        if(loadSplash) {
            setContentView(R.layout.splash_screen);
            TextView splashInfo = (TextView) findViewById(R.id.splashInfoTextView);
            splashInfo.setText("Please make sure that you are aware of your surroundings and other people. \n " +
                    "We recommend that you do not play this game in a busy crowded environment. \n Make sure that you only invite players that you know. \n" +
                    "Players under the age of 13 should play with an adult. \n" +
                    "Player data is not stored after the exit of the game.");
            //Runs at a delay
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this, Home.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 3000); //Specify delay here
            loadSplash = false;
        }
        else {
            //Else load up the Home Activity
            Intent startGameIntent = new Intent(SplashActivity.this, Home.class);
            startGameIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(startGameIntent);
            finish();
        }
    }
}
