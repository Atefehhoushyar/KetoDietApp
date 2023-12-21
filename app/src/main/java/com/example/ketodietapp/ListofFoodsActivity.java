package com.example.ketodietapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListofFoodsActivity extends AppCompatActivity implements DataBaseManager.
        DataBaseManagerInterfaceListener, FoodRecyclerAdapter.FoodListClickListener{

    DataBaseManager dataBaseManager;
    RecyclerView dbList;
    FoodRecyclerAdapter adapter;
    ArrayList<Food> foodlist = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_foods);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.green)));

        dataBaseManager = ((MyApp) getApplication()).dataBaseManager;
        dataBaseManager.getAllFoodInBGThread();
        dataBaseManager.getDb(this);
        dataBaseManager.listener = this;

        dbList = findViewById(R.id.dbList);
        dbList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodRecyclerAdapter(this, foodlist);
        adapter.listener = this;
        dbList.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataBaseManager.listener= this;
        dataBaseManager.getAllFoodInBGThread();

    }

    @Override
    public void databaseGetListOfFoods(List<Food> l) {
        // ready to refresh the list of cites from db
        foodlist=(ArrayList<Food>)l;
        adapter = new FoodRecyclerAdapter(this,foodlist);
       dbList.setAdapter(adapter);
        adapter.listener= this;
//        foodlist= new List<>(l);
//        adapter.setFoodList(foodlist);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void insertFoodCompleted() {

    }

    @Override
    public void onFoodSelected(Food selectedFood) {
        Intent toRecipe = new Intent(this,RecipePageDetailActivity.class);
        toRecipe.putExtra("food",selectedFood);
        startActivity(toRecipe);

    }
}