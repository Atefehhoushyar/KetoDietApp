package com.example.ketodietapp;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.TypeConverters;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity
@TypeConverters(Converters.class)

public class Food implements Parcelable {

    @PrimaryKey(autoGenerate = true)
       int id;
   // @ColumnInfo(name = "Food_name")
    String category;


    String recipe;
    int prepTimeInMinutes;
    int recipeId;
    List<Double> measurements;
    List<String> ingredients;
    List<String> directions;
    String image;
    double calories;
    double fatInGrams;
    double carbohydratesInGrams;
    double proteinInGrams;

    public double getProteinInGrams() {
        return proteinInGrams;
    }

    public Food(String category, String recipe, int prepTimeInMinutes, int recipeId, List<Double> measurements, List<String> ingredients, List<String> directions, String image, double calories, double fatInGrams, double carbohydratesInGrams, double proteinInGrams) {

        this.category = category;
        this.recipe = recipe;
        this.prepTimeInMinutes = prepTimeInMinutes;
        this.recipeId = recipeId;
        this.measurements = measurements;
        this.ingredients = ingredients;
        this.directions = directions;
        this.image = image;
        this.calories = calories;
        this.fatInGrams = fatInGrams;
        this.carbohydratesInGrams = carbohydratesInGrams;
        this.proteinInGrams = proteinInGrams;
    }


    protected Food(Parcel in) {

        category = in.readString();
        recipe = in.readString();
        prepTimeInMinutes = in.readInt();
        recipeId = in.readInt();
        ingredients = in.createStringArrayList();
        directions = in.createStringArrayList();
        image = in.readString();
        calories = in.readDouble();
        fatInGrams = in.readDouble();
        carbohydratesInGrams = in.readDouble();
        proteinInGrams = in.readDouble();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(category);
        dest.writeString(recipe);
        dest.writeInt(prepTimeInMinutes);
        dest.writeInt(recipeId);
        dest.writeStringList(ingredients);
        dest.writeStringList(directions);
        dest.writeString(image);
        dest.writeDouble(calories);
        dest.writeDouble(fatInGrams);
        dest.writeDouble(carbohydratesInGrams);
        dest.writeDouble(proteinInGrams);
    }
}