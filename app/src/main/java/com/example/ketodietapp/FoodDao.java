package com.example.ketodietapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
     void addNewFood(Food f);

    @Query("select * from Food")
    List<Food> getAllFoods();

    @Delete
    void deletFood(Food todelete);
}
