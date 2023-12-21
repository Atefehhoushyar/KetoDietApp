package com.example.ketodietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements NetworkingManager.NetworkingInterfaceListener,
                DataBaseManager.DataBaseManagerInterfaceListener,
                 FoodRecyclerAdapter.FoodListClickListener{

   JsonManager jsonManager;
    NetworkingManager networkingManager;
    FoodRecyclerAdapter adapter;
   // List<Food> foodList;
    ArrayList<Food> list;
    RecyclerView recyclerView;
    FoodRecyclerAdapter listener;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.green)));

        if(savedInstanceState != null){
            list = savedInstanceState.getParcelableArrayList("list");
        }

        networkingManager = ((MyApp)getApplication()).networkingManager;
        jsonManager = ((MyApp)getApplication()).jsonManager;
        networkingManager.listener=this;
        ((MyApp)getApplication()).dataBaseManager.getDb(this);
        list =new ArrayList<>(0);
        ((MyApp)getApplication()).dataBaseManager.listener= this;

        adapter = new FoodRecyclerAdapter(this,list);
        adapter.listener = this;
        recyclerView= findViewById(R.id.foodlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu, menu);
       SearchView menuSearchItem = (SearchView) menu.findItem(R.id.searchbar_menu_item).getActionView();
      menuSearchItem.setQueryHint("Search For Food");
       menuSearchItem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               if (s.length() > 2){
                 networkingManager.getRecipes(s);
               }
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               if (s.length()>2){
                   networkingManager.getRecipes(s);
               }else {
                   adapter.setFoodList(Collections.emptyList());
                   adapter.notifyDataSetChanged();
               }
               return false;
           }
       });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void networkingFinishWithJsonString(String json) {
         List<Food> foodlist = jsonManager.fromJsonToArrayOfFoods(json);
       // list =((MyApp)getApplication()).foodArrayList;
        adapter.setFoodList(foodlist);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void networkingFinishWithBitMapImage(Bitmap bitmap) {

    }

    @Override
    public void onFoodSelected(Food selectedFood) {
        // we want to ask user do you want to save in database ?
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save' " + selectedFood.recipe + "' to the database?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ensure database operation is performed on the background thread
                        ((MyApp) getApplication()).dataBaseManager.addFoodInBGThread(selectedFood);
                        // Update UI on the main thread
                       finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //code to save city to database
//                ((MyApp)getApplication()).dataBaseManager.addFoodInBGThread(selectedFood);
//                finish();
//            }
//        });
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.show();
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list",list);
    }

    @Override
    public void databaseGetListOfFoods(List<Food> l) {

    }

    @Override
    public void insertFoodCompleted() {
        Intent i = new Intent(SearchActivity.this,ListofFoodsActivity.class);
        startActivity(i);
    }
}