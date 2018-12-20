package com.example.tina.js4application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, FoodListAdapter.OnQuantityChange {

    TextView welcome, userEmail, totalEuro;
    Button buyBtn;

    ProgressBar progressBar;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodListAdapter adapter;


    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = findViewById(R.id.food_row);

        layoutManager = new LinearLayoutManager(this);

        getProducts();

        adapter = new FoodListAdapter(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnQuantityChange(this);

        progressBar = findViewById(R.id.prog_bar);

        welcome = findViewById(R.id.welcome_TV);
        userEmail = findViewById(R.id.user_mail);
        totalEuro = findViewById(R.id.euro);

        buyBtn = findViewById(R.id.buy_btn);

        String userMail = getIntent().getStringExtra(MainActivity.welcome);

        userEmail.setText(userMail);

        userEmail.setOnClickListener(this);
        buyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_mail) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra(MainActivity.welcome), null));
            startActivity(Intent.createChooser(emailIntent, "Send email to..."));
        }
    }

    private void getProducts() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://5ba19290ee710f0014dd764c.mockapi.io/Food";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Success", response);
                        try {
                            JSONArray responseJSON = new JSONArray(response);

                            ArrayList<Food> foodArrayList = new ArrayList<>();

                            for (int i = 0; i < responseJSON.length(); i++) {
                                Food food = new Food(responseJSON.getJSONObject(i));
                                foodArrayList.add(food);
                            }

                            adapter.setData(foodArrayList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        }



    public void onItemAdded(double price) {
        total += price;
        progressBar.setProgress(total);
        totalEuro.setText(String.valueOf(total));
        enableBtn();
    }

    public void onItemRemoved(double price) {
        if (total <=0) {
            return;
        }
        total -= price;
        totalEuro.setText(String.valueOf(total));
        enableBtn();
    }

    public void enableBtn(){
        buyBtn.setEnabled(total >= 5);
    }


}

