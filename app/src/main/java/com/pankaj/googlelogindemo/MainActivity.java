package com.pankaj.googlelogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //get the sign in button
        googleSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        //listen to sign in button click
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void sendAccountDetails(String image, String name, String email) {
        Intent goToGoogleUserActivity = new Intent(this, GoogleUserActivity.class);
        goToGoogleUserActivity.putExtra("USER_IMAGE", image);
        goToGoogleUserActivity.putExtra("USER_NAME", name);
        goToGoogleUserActivity.putExtra("USER_EMAIL", email);
        startActivity(goToGoogleUserActivity);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, send authenticated UI.
            //        //get user data from account
            String image = "", name = "", email = "";
            if (account.getPhotoUrl() != null)
                image = account.getPhotoUrl().toString();
            if (account.getDisplayName() != null)
                name = account.getDisplayName();
            if (account.getEmail() != null)
                email = account.getEmail();
            //now send these data to the google sign in activity
            sendAccountDetails(image, name, email);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("MY_APP", "signInResult:failed code=" + e.getStatusCode());
            ;
        }
    }
}
