package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

    public void reportPlayer() {
        //Does some Report Player stuff
    }

    public void sentReport() {
        //Sends Packet To Server
    }
}
