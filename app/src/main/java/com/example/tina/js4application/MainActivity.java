package com.example.tina.js4application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int  PASSWORD_LENGTH = 6;
    public static String welcome = "Welcome";

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    Switch themeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        themeBtn = findViewById(R.id.switch_s);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        themeBtn.setOnCheckedChangeListener
        (new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                themeBtn.getRootView().setBackgroundColor(getResources().getColor(isChecked ? R.color.backgroundD : R.color.backgroundL));
                /*
                if(isChecked) {
                    themeBtn.getRootView().setBackgroundColor(getResources().getColor(R.color.backgroundD));
                }
                else {
                    themeBtn.getRootView().setBackgroundColor(getResources().getColor(R.color.backgroundL));
                }*/
                setColorValueInMemory(isChecked);
            }
        });

        Log.i(TAG,"activity created");
        themeBtn.setChecked(getColorValueFromMemory());
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

           if(!isValidEmail()){
               showErrorMessage(getString(R.string.email_error));
               return;
           }
           if(!isValidPassword()){
               showErrorMessage(getString(R.string.password_error));
               return;
           }
           showSuccesMessage();

           Intent intent = new Intent(this, WelcomeActivity.class);
           String userMail = emailET.getText().toString();
           intent.putExtra(welcome, userMail);
           startActivity(intent);
        }
        else if(view.getId() == R.id.register_btn){
            Intent i = new Intent(this, ActivityRegister.class);
            startActivity(i);
        }
    }

    private void setColorValueInMemory(boolean value){
        editor.putBoolean("BGcolor", value);
        editor.commit();

    }

    private boolean getColorValueFromMemory(){
        return sharePref.getBoolean("BGcolor", false);
    }
}
