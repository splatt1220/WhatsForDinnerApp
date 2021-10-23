package com.cse5236.whatsfordinnerapp;

import androidx.annotation.NonNull;

import com.cse5236.whatsfordinnerapp.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceFoods;
    private List<Food> foods = new ArrayList<>();

    public List<Food> getFoods() {
        return foods;
    }

    public DatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFoods = mDatabase.getReference("Foods");
        readFoods();
    }

    private void readFoods(){
        mReferenceFoods.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foods.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Food food = keyNode.getValue(Food.class);
                    foods.add(food);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("it failed");
            }
        });
    }
}
