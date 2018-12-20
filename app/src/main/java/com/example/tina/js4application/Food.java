package com.example.tina.js4application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Food {

    private String name;
    private  double price;
    private int quantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity(){
        if(quantity == 0){
            return;
        }
        this.quantity--;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Food (String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Food(JSONObject jsonFood) throws JSONException {
        name = jsonFood.getString("name");
        price = jsonFood.getDouble("price");

    }
}
