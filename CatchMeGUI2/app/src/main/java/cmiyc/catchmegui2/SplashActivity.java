package cmiyc.catchmegui2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
