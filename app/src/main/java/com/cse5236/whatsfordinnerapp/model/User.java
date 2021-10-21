package com.cse5236.whatsfordinnerapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the Users in Firebase Database
 */
public class User {
    private String mName, mEmail;
//    private List<Food> mDislikes;

    public User() {
    }

    public User(String name, String email) {
        mName = name;
        mEmail = email;
//        mDislikes = new ArrayList<>();
    }
}
