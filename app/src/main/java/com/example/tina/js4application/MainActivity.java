package com.example.tina.js4application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Patterns.EMAIL_ADDRESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int  PASSWORD_LENGTH = 6;

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
        String email = emailET.getText().toString();
        return (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidPassword(){
        String password = passwordET.getText().toString();
        return (password.length() > PASSWORD_LENGTH);
    }

    private void showErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.i(TAG, getString(R.string.login_error));
    }

    private void showSuccesMessage(){
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
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
           /* if(isValidPassword() && isValidEmail()){
                showSuccesMessage();
            }
            else if(!isValidPassword()){
                showErrorMessagePassword();
            }
            else if(!isValidEmail()){
                showErrorMessageEmail();
            }
            else {
                showErrorMessage();
            }*/

           if(!isValidEmail()){
               showErrorMessage(getString(R.string.email_error));
               return;
           }
           if(!isValidPassword()){
               showErrorMessage(getString(R.string.password_error));
               return;
           }
           showSuccesMessage();

           Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
           startActivity(intent);
        }
        else if(view.getId() == R.id.register_btn){
            //TODO go to register

            Intent i = new Intent(MainActivity.this, ActivityRegister.class);
            startActivity(i);
        }
    }
}
