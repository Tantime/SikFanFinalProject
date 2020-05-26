package com.example.sikfanfinalproject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeList {
    @SerializedName("results")
    private ArrayList<Recipe> recipes;

    public RecipeList() {

    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
