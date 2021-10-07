package com.cse5236.whatsfordinnerapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Class representation of foods data from Foods.json file.
 */
public class Foods {
    List<Food> Foods = new ArrayList<>();

    // return data as a Map
    public Map<String, List<String>> getCategoryToNameList() {
        Map<String, List<String>> categoryToNameList = new HashMap<>();
        for (Food food: Foods) {
            String foodName = food.name;
            String foodCat = food.value;
            if (foodName == null || foodCat == null) {
                try {
                    throw new Exception("Error: Null value");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!categoryToNameList.containsKey(foodCat)) {
                categoryToNameList.put(foodCat, new ArrayList<>());
            }
            List<String> target = categoryToNameList.get(foodCat);
            assert target != null;
            target.add(foodName);
        }
        return categoryToNameList;
    }

    private class Food {
        String name;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
