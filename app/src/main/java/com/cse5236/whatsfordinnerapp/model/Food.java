package com.cse5236.whatsfordinnerapp.model;

/**
 * Helper class for the Foods table in Firebase Database
 */
public class Food {
    private String food_name;
    private String food_type;
    private String link;

    public Food() {
    }

    public Food(String food_name, String food_type, String food_link) {
        this.food_name = food_name;
        this.food_type = food_type;
        this.link = food_link;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getFood_link() {
        return link;
    }

    public void setFood_link(String food_link) {
        this.link = link;
    }
}
