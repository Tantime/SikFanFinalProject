package com.example.sikfanfinalproject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

    String BASE__URL = "https://api.spoonacular.com/";

    @GET("recipes/search?number=6&apiKey=957427d65bac4d389416b3dac39c9d3d")
    Call<ArrayList<Recipe>> getRecipes(@Query("query") String search);

    @GET("recipes/search?number=30&apiKey=957427d65bac4d389416b3dac39c9d3d")
    Call<RecipeList> getRecipeList(@Query("query") String search);

//    @GET("recipeImages/{id}-480x360.jpg")
//    Call<String> getImageURL(@Path("id") String id);

    @GET("recipes/{id}/information?apiKey=957427d65bac4d389416b3dac39c9d3d")
    Call<Recipe> getRecipeById(@Query("id") String id);

    @GET("{id}/analyzedInstructions?apiKey=957427d65bac4d389416b3dac39c9d3d")
    Call<Steps> getStepsById(@Query("id") String id);

}
