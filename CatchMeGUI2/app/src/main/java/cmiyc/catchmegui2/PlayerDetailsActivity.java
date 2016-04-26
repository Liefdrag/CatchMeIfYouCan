package cmiyc.catchmegui2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        /*Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.player_name), Context.MODE_PRIVATE);
        String defaultName = "Player";
        String playerName = sharedPref.getString(getString(R.string.saved_name), defaultName);
        EditText userName = (EditText)findViewById(R.id.playerName);
        userName.setText(playerName);*/ // don't know if this works
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

        //Saving the name to a file stored on the activity, I think...
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.player_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_name), playerName);
        editor.commit();
    }
}
