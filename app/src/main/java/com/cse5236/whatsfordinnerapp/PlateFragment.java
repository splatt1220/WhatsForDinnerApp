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

/**
 * Fragment for Plate screen
 *
 * based on adamcchampion's LoginFragment
 */
public class PlateFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    //package private scope probably
    TextView fruit;
    TextView grain;
    TextView vegetable;
    TextView protein;
    TextView dairy;
    Ingredients ingredients = new Ingredients();

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
        ingredients.getDataFromJson("Foods.json", activity.getApplicationContext());
        // original home code
        fruit = v.findViewById(R.id.fruitText);
        grain = v.findViewById(R.id.grainText);
        vegetable = v.findViewById(R.id.vegetableText);
        protein = v.findViewById(R.id.proteinText);
        dairy = v.findViewById(R.id.dairyText);
        Button shuffleButton = v.findViewById(R.id.shuffleButton);
        shuffleButton.setOnClickListener(this);
        return v;
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
        fruit.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Fruit)));
        grain.setText(getString(R.string.grain_text, ingredients.getRandomIngredient(Ingredients.Category.Grain)));
        vegetable.setText(getString(R.string.vegetable_text, ingredients.getRandomIngredient(Ingredients.Category.Vegetable)));
        protein.setText(getString(R.string.protein_text, ingredients.getRandomIngredient(Ingredients.Category.Protein)));
        dairy.setText(getString(R.string.dairy_text, ingredients.getRandomIngredient(Ingredients.Category.Dairy)));
    }
}
