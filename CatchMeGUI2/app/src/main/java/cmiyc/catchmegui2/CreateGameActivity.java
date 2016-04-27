package cmiyc.catchmegui2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmiyc.catchmegui2.game.UpdateRoomKey;

/**
 * Created by Liefdrag on 11/04/2016.
 */
public class CreateGameActivity extends AppCompatActivity implements UpdateRoomKey{

    protected void onCreate(Bundle savedInstanceState) {
        Home.player.setURKInterface(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);

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
        Button kickButton=(Button)findViewById(R.id.kickFriendButton);
        kickButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                kickFriend();
            }
        });
    }

    private void kickFriend() {
        final String[] players = {"Steve", "Bob", "Johnny", "Joe"}; //Need to get an array of players from Leaderboard
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(players, 0, null)
                //Currently kicks one at a time, could change it to multiple players at later date
                //Need to be careful for host not to kick itself
                .setPositiveButton("KICK A PLAYER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int position = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        String kickedPlayer = players[position]; //Gives the kicked player name
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Do Nothing
                    }
                })
                .show();
    }


    @Override
    public void updateRK(final String key) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView roomKey =(TextView)findViewById(R.id.roomKey);
                roomKey.setText(key);
            }
        });
    }
}
