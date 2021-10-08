package com.cse5236.whatsfordinnerapp;

import androidx.annotation.NonNull;

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

    public interface dataStatus{
        void DataIsLoaded(List<Food> foods);
//        void DataIsInserted();
//        void DataIsUpdated();
//        void DataIsDeleted();
    }

    public DatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFoods = mDatabase.getReference("Foods");
    }


//    public void getReadFoods(){
//        System.out.println(mReferenceFoods.get().toString());
//    }
    public void readFoods(){
        mReferenceFoods.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foods.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Food food = keyNode.getValue(Food.class);
                    System.out.println(food.getFood_name());
                    foods.add(food);
                }
//                listener.DataIsLoaded(foods);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("it failed");
            }
        });
    }
}
