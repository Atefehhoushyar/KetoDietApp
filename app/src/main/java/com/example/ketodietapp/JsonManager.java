package com.example.ketodietapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    public List<Food> fromJsonToArrayOfFoods(String jsonStr) {
        List<Food> foodList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRecipe = jsonArray.getJSONObject(i);


                String recipe = jsonRecipe.getString("recipe");
                JSONObject categoryJson = jsonRecipe.getJSONObject("category");
                String categoryName = categoryJson.getString("category");
                int prepTimeInMinutes = jsonRecipe.getInt("prep_time_in_minutes");
                //int recipeId = jsonRecipe.getInt("id");

                List<Double> measurements = new ArrayList<>();
                for (int j = 1; j <= 10; j++) {
                    double measurement = jsonRecipe.optDouble("measurement_" + j, 0.0);
                    measurements.add(measurement);
                }

                List<String> ingredients = new ArrayList<>();
                for (int j = 1; j <= 10; j++) {
                    String ingredient = jsonRecipe.optString("ingredient_" + j, "");
                    ingredients.add(ingredient);
                }

                List<String> directions = new ArrayList<>();
                for (int j = 1; j <= 10; j++) {
                    String direction = jsonRecipe.optString("directions_step_" + j, "");
                    directions.add(direction);
                }

                String image = jsonRecipe.getString("image");
                double calories = jsonRecipe.getDouble("calories");
                double fatInGrams = jsonRecipe.getDouble("fat_in_grams");
                double carbohydratesInGrams = jsonRecipe.getDouble("carbohydrates_in_grams");
                double proteinInGrams = jsonRecipe.getDouble("protein_in_grams");
                int recipeId = jsonRecipe.getInt("id");

                Food food = new Food(recipe, categoryName,
                        prepTimeInMinutes, recipeId,measurements,ingredients,directions,
                        image, calories, fatInGrams, carbohydratesInGrams, proteinInGrams);

                foodList.add(food);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foodList;
    }
    RecipeInfo fromJsonToRecipeObj(String json){
        RecipeInfo recipeInfo = new RecipeInfo();

        try {
           // JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject recipeObject = jsonArray.getJSONObject(0);
           String category = recipeObject.getJSONObject("category").getString("category");
            recipeInfo.setCategory(recipeObject.getJSONObject("category").getString("category"));
            recipeInfo.setCookTime(recipeObject.getInt("cook_time_in_minutes"));
            recipeInfo.setFatInGrams(recipeObject.getInt("fat_in_grams"));
            recipeInfo.setProteinInGrams(recipeObject.getInt("protein_in_grams"));
            recipeInfo.setCarbohydratesInGrams(recipeObject.getInt("carbohydrates_in_grams"));
            recipeInfo.setImage(recipeObject.getString("image"));
            recipeInfo.setCalories(recipeObject.getInt("calories"));
            recipeInfo.setPrepTime(recipeObject.getInt("prep_time_in_minutes"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return recipeInfo;
    }


}
