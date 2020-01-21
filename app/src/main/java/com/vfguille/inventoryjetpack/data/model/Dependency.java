package com.vfguille.inventoryjetpack.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Dependency implements Parcelable {
    public static final String TAG = "dependency";
    String name;
    String shortName;
    String description;
    String inventory;
    String image;

    public Dependency() {
    }

    public Dependency(String name, String shortName, String description, String inventory, String image) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.inventory = inventory;
        this.image = image;
    }

    protected Dependency(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        inventory = in.readString();
        image = in.readString();
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(shortName);
        parcel.writeString(description);
        parcel.writeString(inventory);
        parcel.writeString(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency that = (Dependency) o;
        return shortName.equals(that.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortName);
    }
}