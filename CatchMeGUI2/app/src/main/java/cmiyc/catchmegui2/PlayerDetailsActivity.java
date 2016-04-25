package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class PlayerDetailsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details);

        Button backButton=(Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button updateDetailsButton = (Button)findViewById(R.id.updateDetailsButton);
        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updatePlayerDetails();
            }
        });
    }

    public void updatePlayerDetails() {
        EditText userName = (EditText)findViewById(R.id.playerName);
        String playerName = userName.getText().toString();
        //Set playerName to something
    }
}
