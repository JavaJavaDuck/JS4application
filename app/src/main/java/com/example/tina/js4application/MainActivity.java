package com.example.tina.js4application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        Log.i(TAG,"activity created");
    }

    private boolean isValidEmail(){
        //TODO how to check id a email is valid??
        return false;
    }

    private boolean isValidPassword(){
        //TODO check the password's lentgh
        if(passwordET.length() >= 6){
            return true;
        }
        return false;
    }

    private showErrorMessage(){
        //TODO show message to the user
        Log.i(TAG, getString(R.string.login_error));
    }

    private showSuccesMessage(){
        //
        Log.i(TAG, getString(R.string.login_success));
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "activity resume");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "activity pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "activity stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "activity destroy");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_btn){

        }
        else if(view.getId() == R.id.register_btn){

        }
    }
}
