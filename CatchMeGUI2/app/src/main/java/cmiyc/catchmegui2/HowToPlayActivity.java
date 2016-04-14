package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class HowToPlayActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);
        Button backHowToPlayButton =(Button)findViewById(R.id.backHowToPlayButton);
        backHowToPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HowToPlayActivity.this, Home.class);
                startActivity(i);
            }
        });
    }
}
