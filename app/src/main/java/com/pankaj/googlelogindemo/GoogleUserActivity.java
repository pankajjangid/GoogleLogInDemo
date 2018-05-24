package com.pankaj.googlelogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GoogleUserActivity extends AppCompatActivity {

    private String mReceivedImage;
    private String mReceivedName;
    private String mReceivedEmail;

    private ImageView mUserImageView;
    private TextView mUserNameTxtView;
    private TextView muserEmailTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_user);

        //init
        mUserImageView = (ImageView)findViewById(R.id.userImage);
        mUserNameTxtView = (TextView)findViewById(R.id.userName);
        muserEmailTxtView = (TextView)findViewById(R.id.userEmail);


        //get user data from intent
        mReceivedImage = getIntent().getStringExtra("USER_IMAGE");
        mReceivedName = getIntent().getStringExtra("USER_NAME");
        mReceivedEmail = getIntent().getStringExtra("USER_EMAIL");

        //set these received data to view
        // Toast.makeText(this, "name " + mReceivedName + " email " + mReceivedEmail + " image " + mReceivedImage, Toast.LENGTH_LONG).show();
        if (!mReceivedImage.isEmpty())
        Picasso.get().load(mReceivedImage).placeholder(R.mipmap.ic_launcher).into(mUserImageView);
        mUserNameTxtView.setText(mReceivedName);
        muserEmailTxtView.setText(mReceivedEmail);

    }
}