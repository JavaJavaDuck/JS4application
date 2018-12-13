package com.example.tina.js4application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView welcome;
    TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.welcome_TV);
        userEmail =findViewById(R.id.user_mail);

        String userMail = getIntent().getStringExtra(MainActivity.welcome);

        userEmail.setText(userMail);

        userEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.user_mail) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra(MainActivity.welcome), null));
            startActivity(Intent.createChooser(emailIntent, "Send email to..."));
        }
    }
}
