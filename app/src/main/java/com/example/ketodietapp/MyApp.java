package com.example.ketodietapp;

import android.app.Application;
import android.os.Looper;
import android.os.Handler;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {

   static MyApp instance;
    JsonManager jsonManager = new JsonManager();
    NetworkingManager networkingManager = new NetworkingManager();
    static ExecutorService executorService = Executors.newFixedThreadPool(4);
    static Handler mainhandler = new Handler(Looper.getMainLooper());
    DataBaseManager dataBaseManager= new DataBaseManager();

    // ArrayList<Food> foodArrayList = new ArrayList<>(0);



    public void onCreate() {
        super.onCreate();
        instance = this;
        jsonManager = new JsonManager();
        networkingManager = new NetworkingManager();
        executorService = Executors.newFixedThreadPool(4);
        mainhandler = new Handler(Looper.getMainLooper());
       // foodArrayList = new ArrayList<>();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public JsonManager getJsonManager() {
        return jsonManager;
    }

    public NetworkingManager getNetworkingManager() {
        return networkingManager;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Handler getMainHandler() {
        return mainhandler;
    }

   // public ArrayList<Food> getFoodArrayList() {
     //   return foodArrayList;
   // }
}
