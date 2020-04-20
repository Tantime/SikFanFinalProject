package com.example.sikfanfinalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {

    private String quantity;
    private String name;
    private String type;

    public Ingredients() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected Ingredients(Parcel in) {
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
