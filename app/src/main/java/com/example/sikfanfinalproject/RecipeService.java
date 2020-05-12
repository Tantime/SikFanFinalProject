package com.example.sikfanfinalproject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeService {

    String BASE__URL = "http://api.spoonacular.com/recipes/";

    @GET("search?query={search}&number=12&apiKey=1b22b06b01d54434a43b80c471e2d144")
    Call<ArrayList<Recipe>> getRecipes(@Path("search") String search);

    @GET("{id}/information?apiKey=1b22b06b01d54434a43b80c471e2d144")
    Call<Recipe> getRecipeById(@Path("id") String id);

    @GET("{id}/analyzedInstructions?apiKey=1b22b06b01d54434a43b80c471e2d144")
    Call<Steps> getStepsById(@Path("id") String id);

}
