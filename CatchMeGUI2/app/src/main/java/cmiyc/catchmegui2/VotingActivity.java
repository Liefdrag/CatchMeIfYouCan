package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cmiyc.catchmegui2.networking.packets.Packet;
import cmiyc.catchmegui2.networking.packets.clientPackets.VotePacket;

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
                playerVoteNull();
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
        RadioGroup radioGroupVote = (RadioGroup) findViewById(R.id.radioGroupVote);
        int voteId = radioGroupVote.getCheckedRadioButtonId();
        RadioButton votedRButton = (RadioButton) findViewById(voteId);
        //This is the game mode picked that needs to be sent to the Server
        VotePacket vp = new VotePacket();
        switch (votedRButton.getText().toString()){
            case "INDIVIDUAL":
                vp.putVote(Packet.GAMETYPE_DEFAULT);
                break;
            case "TEAM":
                vp.putVote(Packet.GAMETYPE_TEAM);
                break;
            case "MANHUNT":
                vp.putVote(Packet.GAMETYPE_MAN_HUNT);
                break;
        }
        Home.player.sendPacket(vp);
    }

    public void playerVoteNull() {
        //When Player skips vote
    }

    public void playerLeft() {
        Home.player.quit();
        //Functionality for when the player leaves
    }
}
