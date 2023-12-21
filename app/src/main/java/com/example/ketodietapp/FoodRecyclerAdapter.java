package com.example.ketodietapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {

    interface FoodListClickListener{
        void onFoodSelected(Food selectedFood);
    }
    Context context;
    private List<Food> foodList;
    FoodListClickListener listener;

    public FoodRecyclerAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =LayoutInflater.from(context).inflate(R.layout.food_row,parent,false);
      // TextView tv = v.findViewById(R.id.foodname);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
         TextView tv=  holder.itemView.findViewById(R.id.foodname);
         tv.setText(foodList.get(position).category + "-" + foodList.get(position).recipe);
//        if (foodList != null && position < foodList.size()) {
//            Food currentFood = foodList.get(position);


           // tv.setText(currentFood.recipe + "-" + currentFood.category);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.onFoodSelected(foodList.get(holder.getAdapterPosition()));
                }
            });
        }


    @Override
    public int getItemCount() {
        return foodList.size();
    }


    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public void addItem(Food food) {
        foodList.add(food);
        notifyItemInserted(foodList.size() - 1);
    }

}



