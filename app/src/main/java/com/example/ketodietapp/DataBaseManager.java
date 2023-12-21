package com.example.ketodietapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.logging.Handler;

public class DataBaseManager {

    interface DataBaseManagerInterfaceListener{
        void databaseGetListOfFoods(List<Food> l);
         void insertFoodCompleted();
    }

    DataBaseManagerInterfaceListener listener;
    FoodDatabase db;
    @SuppressLint("SuspiciousIndentation")
    FoodDatabase getDb(Context context){
        if(db == null)
        db = Room.databaseBuilder(context,
                FoodDatabase.class,"database-Foods").build();
        return db;
    }

    void addFoodInBGThread(Food f){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().addNewFood(f);
                MyApp.mainhandler.post(new Runnable() {
                    @Override

                    public void run() {
                        listener.insertFoodCompleted();
                    }

                });
            }
        });

    }

    void deleteFoodInBGThread(Food f){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().deletFood(f);
            }
        });

    }
    void getAllFoodInBGThread(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
               List<Food> list= db.getDao().getAllFoods();
               MyApp.mainhandler.post(new Runnable() {
                   @Override
                   public void run() {
                       // main thread
                      listener.databaseGetListOfFoods(list);
                   }
               });
            }
        });

    }
}
