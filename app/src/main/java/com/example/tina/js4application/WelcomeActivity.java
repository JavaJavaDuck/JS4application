package com.example.tina.js4application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView welcome;
    TextView userEmail;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = findViewById(R.id.food_row);

        layoutManager = new LinearLayoutManager(this);
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food("Milk", 2.0, 0));
        foodList.add(new Food("Bread", 1.0, 0));
        foodList.add(new Food("Water", 0.5, 0));
        foodList.add(new Food("Tea", 1.0, 0));
        foodList.add(new Food("Coffee", 0.4, 0));
        foodList.add(new Food("Chocolate", 2.5, 0));
        foodList.add(new Food("Cake", 23.0, 0));
        foodList.add(new Food("Pizza", 3.0, 0));
        foodList.add(new Food("Biscuits", 6.0, 0));
        foodList.add(new Food("Croissant", 0.6, 0));

        adapter = new FoodListAdapter(this, foodList);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        welcome = findViewById(R.id.welcome_TV);
        userEmail = findViewById(R.id.user_mail);

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
