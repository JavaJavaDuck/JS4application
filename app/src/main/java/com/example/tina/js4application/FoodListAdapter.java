package com.example.tina.js4application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private ArrayList<Food> data = new ArrayList<>();

    private OnQuantityChange onQuantityChange;

    public void setData(ArrayList<Food> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface OnQuantityChange{
        public void onItemAdded(double price);
        public void onItemRemoved(double price);
    }


    public void setOnQuantityChange(OnQuantityChange onQuantityChange){
        this.onQuantityChange = onQuantityChange;
    }

    public FoodListAdapter(Context context, ArrayList<Food> data){

        this.data = data;
        mInflater = LayoutInflater.from(context);

    }

    public FoodListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override //crea un oggetto che tiene in memoria gli oggetti della lista
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = mInflater.inflate(R.layout.row_item, viewGroup, false);
        return new FoodViewHolder(v);
    }

    @Override //prende l'oggetto che sta per essere visualizzato e la sua posizione
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        FoodViewHolder foodViewHolder = (FoodViewHolder) viewHolder;
        foodViewHolder.productName.setText(data.get(i).getName());
        foodViewHolder.productPrice.setText(String.valueOf(data.get(i).getPrice()));
        foodViewHolder.productQuantity.setText(String.valueOf(data.get(i).getQuantity()));
    }

    @Override //numero di elementi da visualizzare
    public int getItemCount() {
        return data.size();
    }


    //classe innestata che modella la view del singolo elemento della lista
    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView productName, productPrice, productQuantity;
        public Button addBtn, removeBtn;

        public FoodViewHolder(View itemView){
            super(itemView);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            productQuantity = itemView.findViewById(R.id.item_number);
            addBtn = itemView.findViewById(R.id.plus);
            removeBtn = itemView.findViewById(R.id.minus);

            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);

        }

        public void onClick(View view){
            if(view.getId() == R.id.plus){
                Food food = data.get(getAdapterPosition());
                food.increaseQuantity();
                notifyItemChanged(getAdapterPosition());

                onQuantityChange.onItemAdded(food.getPrice());

            }else if(view.getId() == R.id.minus){
                Food food = data.get(getAdapterPosition());
                if(food.getQuantity() > 0){
                    food.decreaseQuantity();
                }
                notifyItemChanged(getAdapterPosition());

                onQuantityChange.onItemRemoved(food.getPrice());
            }
        }
    }
}
