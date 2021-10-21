package com.cse5236.whatsfordinnerapp;

import com.cse5236.whatsfordinnerapp.model.Food;

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

    public static String getRandomIngredient(List<Food> foodList, String foodType) {
        Random rand = new Random();
        List<String> nameList = new ArrayList<>();
        for(Food food : foodList){
            if (food.getFood_type().equals(foodType)){
                nameList.add(food.getFood_name());
            }
        }
        String result = "Not found";
        if (nameList.size() > 0)
            result = nameList.get(rand.nextInt(nameList.size()));

        return result;
    }
}

