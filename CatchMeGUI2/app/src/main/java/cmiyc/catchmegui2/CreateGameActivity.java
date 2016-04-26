package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liefdrag on 11/04/2016.
 */
public class CreateGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);
        TextView roomKey =(TextView)findViewById(R.id.roomKey);
        roomKey.setText("RHEYWJ27"); //Insert Room Key for Room Here

        Button customiseButton =(Button)findViewById(R.id.customiseGameButton);
        customiseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CreateGameActivity.this, CustomiseGameActivity.class);
                startActivity(i);
            }
        });
        Button inviteButton =(Button)findViewById(R.id.inviteFriendButton);
        inviteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CreateGameActivity.this, InvitePlayerActivity.class);
                startActivity(i);
            }
        });
        Button lobbyButton=(Button)findViewById(R.id.lobbyHostButton);
        lobbyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CreateGameActivity.this, LobbyHostActivity.class);
                startActivity(i);
            }
        });
    }


}
