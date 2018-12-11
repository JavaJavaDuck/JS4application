package com.example.tina.js4application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;


public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    private static final String TAGR = "ActivityRegister";
    private static final int  PASSWORD_LENGTH = 6;
    private static final int  PHONE_LENGTH = 9;

    EditText emailETR;
    EditText passwordETR;
    EditText phoneETR;
    Button registerETR;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailETR = findViewById(R.id.email_etr);
        passwordETR = findViewById(R.id.password_etr);
        phoneETR = findViewById(R.id.phone_etr);
        registerETR = findViewById(R.id.register_btnr);

        registerETR.setOnClickListener(this);

    }

    private boolean isValidEmail(){
        String email = emailETR.getText().toString();
        return (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidPassword(){
        String password = passwordETR.getText().toString();
        return (password.length() > PASSWORD_LENGTH);
    }

    private boolean isValidNumber(){
        String phone_number = phoneETR.getText().toString();
        return (phone_number.length() > PHONE_LENGTH);
    }

    private void showErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.i(TAGR, getString(R.string.login_error));
    }

    private void showSuccesMessage(){
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
        Log.i(TAGR, getString(R.string.login_success));
    }

    @Override
    public void onClick(View v) {
        if(!isValidEmail()){
            showErrorMessage(getString(R.string.email_error));
            return;
        }
        if(!isValidPassword()){
            showErrorMessage(getString(R.string.password_error));
            return;
        }
        if(!isValidNumber()){
            showErrorMessage(getString(R.string.number_error));
            return;
        }
        showSuccesMessage();
    }
}

