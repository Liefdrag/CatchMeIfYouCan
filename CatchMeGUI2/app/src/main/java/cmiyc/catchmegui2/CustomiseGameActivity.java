package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

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
        seekBarBoundarySize.getProgress(); //This gets the boundary size
        Switch shrinkingBoundarySwitch =(Switch)findViewById(R.id.shrinkingBoundarySwitch);
        shrinkingBoundarySwitch.isChecked(); //This returns boolean if on or off
        EditText timeLimit =(EditText)findViewById(R.id.timeLimit);
        timeLimit.getText().toString(); //Returns the time limit
        EditText scoreLimit = (EditText)findViewById(R.id.scoreLimit);
        scoreLimit.getText().toString(); //Returns the score limit
        RadioGroup modeRGroup = (RadioGroup)findViewById(R.id.modeRGroup);
        switch (modeRGroup.getCheckedRadioButtonId()) {
            case 0:
                //Game mode is individual
                break;
            case 1:
                //Game mode is team
                break;
            case 2:
                //Game mode is manhunt
                break;
            default:
                //No Game Mode Selected
                break;
        }
        /*
        Work out which radio button has been picked
         */
        Switch votingEnabledSwitch =(Switch)findViewById(R.id.votingEnabledSwitch);
        votingEnabledSwitch.isChecked(); //This returns boolean if on or off
    }
}
