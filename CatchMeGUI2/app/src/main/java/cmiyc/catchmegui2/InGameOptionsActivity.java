package cmiyc.catchmegui2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class InGameOptionsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame_options);
        Button backInGameButton =(Button)findViewById(R.id.backGameOptionsButton);
        backInGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button abilitiesButton =(Button)findViewById(R.id.abilitiesButton);
        abilitiesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseAbility();
            }
        });
        Button quitInGameButton =(Button)findViewById(R.id.quitInGameButton);
        quitInGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                quitGame();
            }
        });
        Button reportPlayerButton =(Button)findViewById(R.id.reportPlayerButton);
        reportPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reportPlayer();
            }
        });
        Button reportBugButton =(Button)findViewById(R.id.reportBugButton);
        reportBugButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sentReport();
            }
        });
    }

    public void quitGame() {
        //Leaving the game functionality
        Intent i = new Intent(InGameOptionsActivity.this, Home.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void chooseAbility() {
        final String[] abilities = {"HIDE", "PING", "DECOY", "SNEAK"};
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(abilities, 0, null)
                .setPositiveButton("USE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int position = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        String usedAbility = abilities[position]; //Gives the ability used
                        //MAY WANT TO DISPLAY DIALOG FOR A BIT
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Do Nothing
                    }
                })
                .show();
    }

    public void reportPlayer() {
        final String[] players = {"Steve", "Bob", "Johnny", "Joe"}; //Need to get an array of players from Leaderboard
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(players, 0, null)
                .setPositiveButton("REPORT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int position = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        String reportPlayer = players[position]; //Gives the reported player name
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Do Nothing
                    }
                })
                .show();
    }

    public void sentReport() {
        AlertDialog.Builder report = new AlertDialog.Builder(this);
        report.setTitle("Report Bug");

        final EditText input = new EditText (InGameOptionsActivity.this);
        //Makes the EditText visible on Dialog and also allows for increase in size
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        //Puts the EditText into a dialog's view
        report.setView(input);

        report.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String report = input.getText().toString();
                //Then send report to Server
            }
        });
        report.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do Nothing
            }
        });
        report.show();
    }
}
