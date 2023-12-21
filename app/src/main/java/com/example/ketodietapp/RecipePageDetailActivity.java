package com.example.ketodietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipePageDetailActivity extends AppCompatActivity implements NetworkingManager.NetworkingInterfaceListener {

    TextView Name,PrepTime,CookTime,cal,fat,carb,protein;
    ImageView foodImage;
    NetworkingManager networkingManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.green)));

        Food f = getIntent().getExtras().getParcelable("food");
       // this.setTitle(f.food);
        networkingManager= ((MyApp)getApplication()).networkingManager;
        networkingManager.listener= this;
       // networkingManager.getRecipes(f.toString());// write a function to get details of our recipe in networking manager
        networkingManager.getRecipeDetails(f.recipeId);

       Name = findViewById(R.id.recipe_Name_text);
        PrepTime = findViewById(R.id.prepTime_text);
        foodImage = findViewById(R.id.foodImage);
        cal = findViewById(R.id.cal_text);
        fat = findViewById(R.id.fat_text);
        carb=findViewById(R.id.carb_text);
        protein= findViewById(R.id.protein_text);






    }

    @Override
    public void networkingFinishWithJsonString(String json) {
         RecipeInfo recipeInfo=((MyApp)getApplication()).jsonManager.fromJsonToRecipeObj(json);
        networkingManager.downloadImage(recipeInfo.getImage());
         Name.setText("Recipe Name:"+"  "+ recipeInfo.getName());
        PrepTime.setText(String.valueOf("Prep_time:" + recipeInfo.prepTime));
        //CookTime.setText(String.valueOf("Cook_time:" + recipeInfo.cookTime));
        cal.setText("Calories:" +""+ recipeInfo.getCalories());
        fat.setText("Fat_In_Grams:" +""+ recipeInfo.getFatInGrams());
        carb.setText("Carbohydrates_In_Grams:" +""+ recipeInfo.getCarbohydratesInGrams());
        protein.setText("Protein_In_Grams:" +"" + recipeInfo.getProteinInGrams());

        networkingManager.downloadImage(recipeInfo.getImage());
    }

    @Override
    public void networkingFinishWithBitMapImage(Bitmap bitmap) {

        foodImage.setImageBitmap(bitmap);
    }
}