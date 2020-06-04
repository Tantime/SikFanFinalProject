package com.example.sikfanfinalproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    private String id;
    private String title;
    private ArrayList<Ingredients> extendedIngredients;
    private Steps steps;
    private String image;

    public Recipe() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Ingredients> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(ArrayList<Ingredients> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public Steps getSteps() {
        return steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", extendedIngredients=" + extendedIngredients +
                ", steps=" + steps +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeTypedList(this.extendedIngredients);
        dest.writeParcelable(this.steps, flags);
        dest.writeString(this.image);
    }

    protected Recipe(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.extendedIngredients = in.createTypedArrayList(Ingredients.CREATOR);
        this.steps = in.readParcelable(Steps.class.getClassLoader());
        this.image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
