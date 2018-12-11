package com.example.tina.js4application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome;
    TextView userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.welcome_TV);
        userEmail =findViewById(R.id.user_mail);

        String userMail = getIntent().getStringExtra(MainActivity.welcome);

        userEmail.setText(userMail);
    }
}
