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

        Button submitButton=(Button)findViewById(R.id.submitVoteButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countVote();
                Intent i = new Intent(VotingActivity.this, LobbyActivity.class);
                startActivity(i);
            }
        });

        Button skipButton=(Button)findViewById(R.id.skipVoteButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerVote(0);
                Intent i = new Intent(VotingActivity.this, LobbyActivity.class);
                startActivity(i);
            }
        });
        Button backVotingButton=(Button)findViewById(R.id.leaveVoteButton);
        backVotingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playerLeft();
                finish();
            }
        });
    }

    public void countVote() {
        //Functionality to count the vote
    }

    public void playerVote(int vote) {
        //For when player votes
        //Currently when argument is 0 then player skipped the vote.
    }

    public void playerLeft() {
        //Functionality for when the player leaves
    }
}
