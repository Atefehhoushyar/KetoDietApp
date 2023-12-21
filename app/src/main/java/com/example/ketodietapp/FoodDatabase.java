package com.example.ketodietapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Food.class},version = 1)
@TypeConverters(Converters.class)
public abstract class FoodDatabase extends RoomDatabase {

    public  abstract FoodDao getDao();//build food database
}
