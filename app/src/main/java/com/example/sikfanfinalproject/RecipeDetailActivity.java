package com.example.sikfanfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sikfanfinalproject.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private ImageView imageViewImage;
    private TextView textViewIngredients;
    private TextView textViewSteps;

    private static final String TAG = "RecipeDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wireWidgets();

        Intent lastIntent = getIntent();
        Recipe recipe = lastIntent.getParcelableExtra(HomeFragment.EXTRA_RECIPE);
        Log.d(TAG, "onCreate: " + recipe);

        textViewName.setText("" + recipe.getName());
        Picasso.get().load(recipe.getImageURL()).into(imageViewImage);
    }

    private void wireWidgets() {
        textViewName = findViewById(R.id.textView_recipeDetail_name);
        imageViewImage = findViewById(R.id.imageView_recipeDetail_image);
        textViewIngredients = findViewById(R.id.textView_recipeDetail_ingredients);
        textViewSteps = findViewById(R.id.textView_recipeDetail_steps);
    }
}
