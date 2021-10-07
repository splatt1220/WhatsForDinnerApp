package com.cse5236.whatsfordinnerapp;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * Ingredients of a plate
 */
public class Ingredients {
    private Map<String, List<String>> categoryToNameList;

    public enum Category {
        Fruit,
        Grain,
        Vegetable,
        Protein,
        Dairy,
    }

    // constructor
    public Ingredients() {
        categoryToNameList = new HashMap<>();
    }

    // get data from a json file using gson
    public void getDataFromJson(String filename, Context context) {
        String stringFromJson = Utils.getJsonFromAssets(context, filename);
        Gson gson = new Gson();
        Foods foods = gson.fromJson(stringFromJson, Foods.class);
        categoryToNameList = foods.getCategoryToNameList();
    }

    // return a food randomly from the passed category.
    public String getRandomIngredient(Category cat) {
        Random rand = new Random();
        List<String> nameList = categoryToNameList.get(cat.name());

        String result = "Not found";
        if (nameList != null)
            result = nameList.get(rand.nextInt(nameList.size()));

        return result;
    }
}
