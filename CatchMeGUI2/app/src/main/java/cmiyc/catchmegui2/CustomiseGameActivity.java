package cmiyc.catchmegui2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import cmiyc.catchmegui2.networking.packets.Packet;
import cmiyc.catchmegui2.networking.packets.clientPackets.VotePacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.BoundaryUpdatesPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.GametypePacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.ScoreLimitPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.SetBoundariesPacket;
import cmiyc.catchmegui2.networking.packets.clientPackets.hostPackets.TimeLimitPacket;

/**
 * Created by Liefdrag on 11/04/2016.
 */
public class CustomiseGameActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customise_game);
        Button backCustomiseButton =(Button)findViewById(R.id.backCustomiseButton);
        backCustomiseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button updateDetailsCustomiseButton =(Button)findViewById(R.id.updateDetailsCustomiseButton);
        updateDetailsCustomiseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customiseSettings();
                finish();
            }
        });
    }

    public void customiseSettings() {
        SeekBar seekBarBoundarySize =(SeekBar)findViewById(R.id.seekBarBoundarySize);
        int size = seekBarBoundarySize.getProgress(); //This gets the boundary size
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Checks if the location permissions have been enabled
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            }
        }
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        double latitude = 0;
        double longitude = 0;
        if(location!=null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        SetBoundariesPacket sbp = new SetBoundariesPacket();
        sbp.putBoundaries(longitude, latitude, size);
        Home.player.sendPacket(sbp);

        Switch shrinkingBoundarySwitch =(Switch)findViewById(R.id.shrinkingBoundarySwitch);
        boolean shrink = shrinkingBoundarySwitch.isChecked(); //This returns boolean if on or off
        BoundaryUpdatesPacket bup = new BoundaryUpdatesPacket();
        bup.putBoundaryUpdates(60, 20);
        Home.player.sendPacket(bup);

        EditText timeLimit =(EditText)findViewById(R.id.timeLimit);
        try{
            int time = Integer.parseInt(timeLimit.getText().toString()); //Returns the time limit
            TimeLimitPacket tlp = new TimeLimitPacket();
            tlp.putTimeLimit(time);
            Home.player.sendPacket(tlp);
        } catch (NumberFormatException nfe){
            //
        }


        EditText scoreLimit = (EditText)findViewById(R.id.scoreLimit);
        try{
            int score = Integer.parseInt(scoreLimit.getText().toString()); //Returns the time limit
            ScoreLimitPacket slp = new ScoreLimitPacket();
            slp.putScoreLimit(score);
            Home.player.sendPacket(slp);
        } catch (NumberFormatException nfe){
            //
        }

         //Returns the score limit
        RadioGroup modeRGroup = (RadioGroup)findViewById(R.id.modeRGroup);
        GametypePacket gtp = new GametypePacket();
        switch (modeRGroup.getCheckedRadioButtonId()) {
            case 0:
                //Game mode is individual
                gtp.putGameType(Packet.GAMETYPE_DEFAULT);
                break;
            case 1:
                gtp.putGameType(Packet.GAMETYPE_TEAM);
                //Game mode is team
                break;
            case 2:
                gtp.putGameType(Packet.GAMETYPE_MAN_HUNT);
                //Game mode is manhunt
                break;
            default:
                //No Game Mode Selected
                break;
        }
        Home.player.sendPacket(gtp);
        /*
        Work out which radio button has been picked
         */
        Switch votingEnabledSwitch =(Switch)findViewById(R.id.votingEnabledSwitch);
        VotePacket vp = new VotePacket();
        if(votingEnabledSwitch.isChecked()){
            vp.putVote(Packet.VOTES_ENABLED);
        } else { //This returns boolean if on or off
            vp.putVote(Packet.VOTES_DISABLED);
        }
    }
}
