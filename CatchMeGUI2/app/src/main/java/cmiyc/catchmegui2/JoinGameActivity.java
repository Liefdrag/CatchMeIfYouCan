package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 12/04/2016.
 */
public class JoinGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_game);
        Button Button1 = (Button)findViewById(R.id.joinGameLobbyButton);
        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterRoom();

            }
        });
        Button Button2 = (Button)findViewById(R.id.backjoinGameButton);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(JoinGameActivity.this, Home.class);
                finish();
            }
        });
    }

    public void enterRoom() {
        //Retrieves the inserted key from editText
        //Sends the room key to the server
        //If room key is wrong then needs to be an error screen
        // else if correct
        Intent i = new Intent(JoinGameActivity.this, VotingActivity.class);
        startActivity(i);
    }
}
