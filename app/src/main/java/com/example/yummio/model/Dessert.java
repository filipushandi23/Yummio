package com.example.yummio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dessert implements Parcelable {
    @SerializedName("id")
    private int id;

   @SerializedName("name")
    private String name;

   @SerializedName("ingredients")
    private List<Ingredients> ingredients = new ArrayList<Ingredients>();

   @SerializedName("steps")
    private List<Steps> steps = new ArrayList<Steps>();

   @SerializedName("servings")
    private int servings;

   @SerializedName("image")
    private String image;

    public Dessert(){}

    public Dessert(int id, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Dessert(Parcel p_parcel) {
        this.id = p_parcel.readInt();
        this.name = p_parcel.readString();
        this.servings = p_parcel.readInt();
        this.image = p_parcel.readString();
        p_parcel.readTypedList(ingredients, Ingredients.CREATOR);
        p_parcel.readTypedList(steps, Steps.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel p_parcel, int i) {
        p_parcel.writeInt(this.id);
        p_parcel.writeString(this.name);
        p_parcel.writeInt(this.servings);
        p_parcel.writeString(this.image);
        p_parcel.writeTypedList(ingredients);
        p_parcel.writeTypedList(steps);

    }
}
