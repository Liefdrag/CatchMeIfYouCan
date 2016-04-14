package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class VotingActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voting);

        Button skipButton=(Button)findViewById(R.id.skipVoteButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(VotingActivity.this, LobbyActivity.class);
                startActivity(i);
            }
        });
        Button backVotingButton=(Button)findViewById(R.id.leaveVoteButton);
        backVotingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(VotingActivity.this, Home.class);
                startActivity(i);
            }
        });
    }
}
