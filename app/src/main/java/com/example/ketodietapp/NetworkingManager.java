package com.example.ketodietapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkingManager {
    interface NetworkingInterfaceListener {
        void networkingFinishWithJsonString(String json);

        void networkingFinishWithBitMapImage(Bitmap bitmap);
    }

    NetworkingInterfaceListener listener;

    void getRecipes(String query) {
        String urlString = "https://keto-diet.p.rapidapi.com/?protein_in_grams__lt=15&protein_in_grams__gt=5";
        connect(urlString);
    }

//    void getRecipes(String query){
//        String urlString ="https://keto-diet.p.rapidapi.com/?search=beef";
//        connect(urlString);
//    }
    void getRecipeDetails(int recipeId) {
        String urlString = "https://keto-diet.p.rapidapi.com/?id=" + String.valueOf(recipeId);
        connect(urlString);
    }


    void connect(String urlString) {
        MyApp.executorService.execute(new Runnable() {
            String jsonResponse;

            @Override
            public void run() {
                HttpsURLConnection httpsURLConnection = null;
                try {
                    URL urlObj = new URL(urlString);
                    httpsURLConnection = (HttpsURLConnection) urlObj.openConnection();
                    httpsURLConnection.setRequestProperty("X-RapidAPI-Key", "3c8d1a5d7amshd90430984c3016dp1bcad1jsnde3f88b9f9af");
                    httpsURLConnection.setRequestProperty("X-RapidAPI-Host", "keto-diet.p.rapidapi.com");
                    httpsURLConnection.setRequestMethod("GET");
                    InputStream inputStr = httpsURLConnection.getInputStream();
                    String encoding = httpsURLConnection.getContentEncoding() == null ? "UTF-8"
                            : httpsURLConnection.getContentEncoding();
                    StringBuffer buffer = new StringBuffer();
                    int v;
                    while ((v = inputStr.read()) != -1) {
                        buffer.append((char) v);
                    }
                    jsonResponse = buffer.toString();
                    Log.d("json", jsonResponse);
                    // }

                    MyApp.mainhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // run in main thread
                            listener.networkingFinishWithJsonString(jsonResponse);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("There is an error");
                    e.printStackTrace();
                } finally {
                    httpsURLConnection.disconnect();
                }

            }
        });
    }

    void downloadImage(String imageUrl) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.get().load(imageUrl).get();

                    Bitmap finalBitmap = bitmap;
                    MyApp.mainhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.networkingFinishWithBitMapImage(finalBitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}