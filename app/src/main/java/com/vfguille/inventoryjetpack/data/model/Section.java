package com.vfguille.inventoryjetpack.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Section implements Parcelable {

    public static final String TAG = "section";
    @NonNull
    String name;
    @PrimaryKey
    @NonNull
    String shortName;
    @NonNull
    String dependency;
    @NonNull
    String description;
    @NonNull
    String image;

    public Section(String name, String shortName, String dependency, String description, String image) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.image = image;
    }

    public Section() {

    }

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

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
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
        return shortName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Section(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        dependency = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public static final Creator<Section> CREATOR = new Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(shortName);
        parcel.writeString(dependency);
        parcel.writeString(description);
        parcel.writeString(image);
    }
}
