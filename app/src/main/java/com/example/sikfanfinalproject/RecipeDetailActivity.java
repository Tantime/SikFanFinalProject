package com.example.sikfanfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView imageViewImage;
    private TextView textViewIngredients;
    private TextView textViewSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        wireWidgets();

        Intent lastIntent = getIntent();
        Recipe recipe = lastIntent.getParcelableExtra(RecipeListActivity.EXTRA_RECIPE);

        textViewName.setText("" + recipe.getName());
        int resourceImage = getResources().getIdentifier(recipe.getImageURL(), "drawable", getPackageName());
        imageViewImage.setImageDrawable(getResources().getDrawable(resourceImage));
        textViewIngredients.setText("" + recipe.getIngredients());
        textViewSteps.setText("" + recipe.getSteps());
    }

    private void wireWidgets() {
        textViewName = findViewById(R.id.textView_recipeDetail_name);
        imageViewImage = findViewById(R.id.imageView_recipeDetail_image);
        textViewIngredients = findViewById(R.id.textView_recipeDetail_ingredients);
        textViewSteps = findViewById(R.id.textView_recipeDetail_steps);
    }
}
