package com.example.ketodietapp;

import java.util.List;

public class RecipeInfo {

    static int prepTime;
    static String category;


    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        RecipeInfo.category = category;
    }

    static int cookTime;

    static String image;
    static double calories;
    static double fatInGrams;
    static double carbohydratesInGrams;
    static double proteinInGrams;

    public RecipeInfo() {
    }

    public RecipeInfo(int prepTime, int cookTime, String image, double calories, double fatInGrams, double carbohydratesInGrams, double proteinInGrams) {
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.image = image;
        this.calories = calories;
        this.fatInGrams = fatInGrams;
        this.carbohydratesInGrams = carbohydratesInGrams;
        this.proteinInGrams = proteinInGrams;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFatInGrams(double fatInGrams) {
        this.fatInGrams = fatInGrams;
    }

    public void setCarbohydratesInGrams(double carbohydratesInGrams) {
        this.carbohydratesInGrams = carbohydratesInGrams;
    }

    public void setProteinInGrams(double proteinInGrams) {
        this.proteinInGrams = proteinInGrams;
    }

    public static int getPrepTime() {
        return prepTime;
    }

    public static int getCookTime() {
        return cookTime;
    }

    public static String getImage() {
        return image;
    }

    public static double getCalories() {
        return calories;
    }

    public static double getFatInGrams() {
        return fatInGrams;
    }

    public static double getCarbohydratesInGrams() {
        return carbohydratesInGrams;
    }

    public static double getProteinInGrams() {
        return proteinInGrams;
    }
}