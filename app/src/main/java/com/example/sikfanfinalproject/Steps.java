package com.example.sikfanfinalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    private String step;
    private String number;

    public Steps() {

    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Creator<Steps> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.step);
        dest.writeString(this.number);
    }

    protected Steps(Parcel in) {
        this.step = in.readString();
        this.number = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
