package com.example.tina.js4application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG,"activity created");
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
}
