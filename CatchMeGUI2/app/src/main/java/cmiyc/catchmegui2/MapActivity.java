package cmiyc.catchmegui2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class MapActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
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
}
