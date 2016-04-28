package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class HowToPlayActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);
        TextView howToPlay = (TextView)findViewById(R.id.howToPlayText);
        String play = "Main Menu: Press Create Game to create a new lobby where you become the host. \n" +
                "Main Menu: Press Join Game to go to a page where you can enter a room key to join a game \n" +
                "Main Menu: Press Player Details to change your name and sign in your Google Account \n" +
                "(HOST) Create Game: Press Invite Player to go to a screen where you can send the room key to specified Players \n" +
                "(HOST) Create Game: Press Kick Player to display a dialog box to choose a Player to kick \n" +
                "(HOST) Create Game: Press Customise Settings to change the game settings \n" +
                "(HOST) Create Game: Press See Lobby to see the lobby \n" +
                "(HOST) Lobby: Swipe right to see all the Players in the lobby in the leaderboard, then swipe back left to go back to lobby \n" +
                "(HOST) Lobby: Press Back to go back to the previous screen, press Leave Game to leave the game and press Start Game to start the game \n" +
                "In-Game: Swipe right to see the leaderboard then swipe back left to return to main game screen. Swipe left to see the map of your position and a circular boundary" +
                "and then swipe back right to return to main screen. \n" +
                "In-Game: Press Caught Target when you see your target to catch them \n" +
                "In-Game: Been Caught button will only be enabled when your pursuer presses their caught target button. Once you have been caught then you should press the Been Caught button \n" +
                "In-Game: Press options to navigate to an options page where you can use abilities, report a player, report a bug and leave the game \n" +
                "In-Game: The compass on the main screen will direct you to your target. Each time you catch your target, your score will increase and your target will change. \n" +
                "Join Game: Enter a room key to join the lobby of a game. If successful then the player can vote for the game mode of their choosing. Afterwards they will enter the lobby and will need to wait for when the host starts the game \n" +
                "Player Details: The player can change their displayed name or can sign in with their Google Account and use that name. \n\n" +
                "Aim of Game: \n" +
                "Capture the most players and try to escape capture. \n" +
                "Tip: Try to be sneaky and quiet. The quieter you are, the hard it is to spot you \n\n" +
                "HEALTH AND SAFETY: MAKE SURE YOU ARE AWAKE OF YOUR SURROUNDING ENVIRONMENT AND THE PUBLIC. DO NOT PLAY IF YOU WILL DISTURB PEOPLE WHO AREN'T PLAYING THE GAME";
        howToPlay.setText(play);

        Button backHowToPlayButton =(Button)findViewById(R.id.backHowToPlayButton);
        backHowToPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
