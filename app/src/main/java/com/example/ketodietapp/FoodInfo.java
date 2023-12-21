package com.example.ketodietapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
@TypeConverters(Converters.class)

public class FoodInfo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    // @ColumnInfo(name = "Food_name")
    String category;
    int recipeId;
    String recipe;
    int prepTimeInMinutes;
    int cookTimeInMinutes;
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

    public FoodInfo(int recipeId,String category, String recipe, int prepTimeInMinutes, int cookTimeInMinutes, List<Double> measurements, List<String> ingredients, List<String> directions, String image, double calories, double fatInGrams, double carbohydratesInGrams, double proteinInGrams) {
        this.recipeId=recipeId;
        this.category = category;
        this.recipe = recipe;
        this.prepTimeInMinutes = prepTimeInMinutes;
        this.cookTimeInMinutes = cookTimeInMinutes;
        this.measurements = measurements;
        this.ingredients = ingredients;
        this.directions = directions;
        this.image = image;
        this.calories = calories;
        this.fatInGrams = fatInGrams;
        this.carbohydratesInGrams = carbohydratesInGrams;
        this.proteinInGrams = proteinInGrams;
    }


    protected FoodInfo(Parcel in) {
        recipeId=in.readInt();
        category = in.readString();
        recipe = in.readString();
        prepTimeInMinutes = in.readInt();
        cookTimeInMinutes = in.readInt();
        ingredients = in.createStringArrayList();
        directions = in.createStringArrayList();
        image = in.readString();
        calories = in.readDouble();
        fatInGrams = in.readDouble();
        carbohydratesInGrams = in.readDouble();
        proteinInGrams = in.readDouble();
    }

    public static final Creator<FoodInfo> CREATOR = new Creator<FoodInfo>() {
        @Override
        public FoodInfo createFromParcel(Parcel in) {
            return new FoodInfo(in);
        }

        @Override
        public FoodInfo[] newArray(int size) {
            return new FoodInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(recipeId);
        dest.writeString(category);
        dest.writeString(recipe);
        dest.writeInt(prepTimeInMinutes);
        dest.writeInt(cookTimeInMinutes);
        dest.writeStringList(ingredients);
        dest.writeStringList(directions);
        dest.writeString(image);
        dest.writeDouble(calories);
        dest.writeDouble(fatInGrams);
        dest.writeDouble(carbohydratesInGrams);
        dest.writeDouble(proteinInGrams);
    }
}
