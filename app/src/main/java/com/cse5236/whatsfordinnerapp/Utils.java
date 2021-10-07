package com.cse5236.whatsfordinnerapp;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Utility class
 */
public class Utils {

    public enum Category {
        Fruit,
        Grain,
        Vegetable,
        Protein,
        Dairy,
    }

    // parse json file to a string and return
    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = is.read(buffer);
            is.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public static String getRandomIngredient(List<Food> foodList, String foodType) {
        Random rand = new Random();
        List<String> nameList = new ArrayList<>();
        for(Food food : foodList){
            if (food.getFood_type() == foodType){
                nameList.add(food.getFood_name());
            }
        }
        String result = "Not found";
        if (nameList != null)
            result = nameList.get(rand.nextInt(nameList.size()));

        return result;
    }
}

