package cmiyc.catchmegui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.PlusShare;

/**
 * Created by Liefdrag on 11/04/2016.
 * Code is inspired by guide on Google Developer website
 */
public class InvitePlayerActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final int REQUEST_INVITE = 0;
    //private GoogleApiClient mGoogleApiClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_player);
        findViewById(R.id.invite_button).setOnClickListener(this);
        findViewById(R.id.share_button).setOnClickListener(this);
        findViewById(R.id.backInviteButton).setOnClickListener(this);

        /* Used with app invites
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();*/
    }

    //Methods called when certain buttons are clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.invite_button:
                onInviteClicked();
                break;
            case R.id.share_button:
                shareGooglePlus();
                break;
            case R.id.backInviteButton:
                finish();
                break;
        }
    }

    /**
     * Shares a room key to certain friends on google+
     */
    private void shareGooglePlus() {
        Intent shareIntent = new PlusShare.Builder(this)
                .setType("text/plain")
                .setText("ROOM KEY: " + Home.player.getRoomKey())
                .getIntent();
        startActivityForResult(shareIntent, 0);
    }

    /**
     * Tries to send app invite + Room Key to friends
     */
    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder("Room Key")
                .setMessage(Home.player.getRoomKey())
                //.setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                //.setCustomImage(Uri.parse(R.mipmap.ic_launcher))
                .setCallToActionText("Test")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
