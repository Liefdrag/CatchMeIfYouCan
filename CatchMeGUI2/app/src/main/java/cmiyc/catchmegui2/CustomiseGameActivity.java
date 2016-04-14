package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 11/04/2016.
 */
public class CustomiseGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customise_game);
        Button backCustomiseButton =(Button)findViewById(R.id.backCustomiseButton);
        backCustomiseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
