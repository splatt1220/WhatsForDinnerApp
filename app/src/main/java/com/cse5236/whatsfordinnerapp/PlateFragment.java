package com.cse5236.whatsfordinnerapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for Plate screen
 *
 * based on adamcchampion's LoginFragment
 */
public class PlateFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    //package private scope probably
    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private List<Food> foods = new ArrayList<>();

    private TextView fruit;
    private TextView grain;
    private TextView vegetable;
    private TextView protein;
    private TextView dairy;

    private String[] currentPicks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plate, container, false);

        Activity activity = requireActivity();
        // original home code
        fruit = v.findViewById(R.id.fruitText);
        grain = v.findViewById(R.id.grainText);
        vegetable = v.findViewById(R.id.vegetableText);
        protein = v.findViewById(R.id.proteinText);
        dairy = v.findViewById(R.id.dairyText);

        currentPicks = new String[5];

        Button shuffleButton = v.findViewById(R.id.shuffleButton);
        shuffleButton.setOnClickListener(this);

        return v;
    }
    @Override
    public void onStart() {
        super.onStart();

        currentPicks[0] = getString(R.string.fruit_text);
        fruit.setText(currentPicks[0]);
        currentPicks[1] = getString(R.string.grain_text);
        grain.setText(currentPicks[1]);
        currentPicks[2] = getString(R.string.vegetable_text);
        vegetable.setText(currentPicks[2]);
        currentPicks[3] = getString(R.string.protein_text);
        protein.setText(currentPicks[3]);
        currentPicks[4] = getString(R.string.dairy_text);
        dairy.setText(currentPicks[4]);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


    @Override
    public void onClick(View view) {
        foods = databaseHelper.getFoods();

        currentPicks[0] = getString(R.string.fruit_text, Utils.getRandomIngredient(foods, "Fruit"));
        fruit.setText(currentPicks[0]);
        currentPicks[1] = getString(R.string.grain_text, Utils.getRandomIngredient(foods, "Grain"));
        grain.setText(currentPicks[1]);
        currentPicks[2] = getString(R.string.vegetable_text, Utils.getRandomIngredient(foods, "Vegetable"));
        vegetable.setText(currentPicks[2]);
        currentPicks[3] = getString(R.string.protein_text, Utils.getRandomIngredient(foods, "Protein"));
        protein.setText(currentPicks[3]);
        currentPicks[4] =getString(R.string.dairy_text, Utils.getRandomIngredient(foods, "Dairy"));
        dairy.setText(currentPicks[4]);
    }
}
