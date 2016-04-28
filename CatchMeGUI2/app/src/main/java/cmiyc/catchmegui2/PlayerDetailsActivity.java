package cmiyc.catchmegui2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;

/**
 * Created by Liefdrag on 13/04/2016.
 */
public class PlayerDetailsActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    EditText userName;
    public static Uri avatarPhoto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details);


        userName = (EditText)findViewById(R.id.playerName);
        Button backButton=(Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button updateDetailsButton = (Button)findViewById(R.id.updateDetailsButton);
        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updatePlayerDetails();
            }
        });

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.btn_logout).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        ////////GOOGLE SIGN IN STUFF /////////////////////////
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .build();
    }

    //Click listeners
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btn_logout:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;
        }
    }

    //Method to get the User's name
    public void updatePlayerDetails() {
        String playerName = userName.getText().toString();
        //Set playerName to something
    }

    //Method that is called when user signs in with Google Account
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //Method that is called when user signs out with Google Account
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        userName.setText("",TextView.BufferType.EDITABLE);
                    }
                });
    }

    //Method called when sign in successful. It is the result of the sign in intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    //Method that handles the Google Account sign in.
    //Can be used to set the player's name, email, id and avatar
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            userName.setText(acct.getDisplayName(), TextView.BufferType.EDITABLE);
            String personName = acct.getDisplayName(); //Gets the users name
            String personEmail = acct.getEmail(); //Gets the users email
            String personId = acct.getId(); //Gets the users ID
            avatarPhoto = acct.getPhotoUrl(); //Gets the users photo
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    //Method that revokes the phones access to the Google Account
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
