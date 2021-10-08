package com.cse5236.whatsfordinnerapp;

import static com.cse5236.whatsfordinnerapp.Utils.getRandomIngredient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    DatabaseHelper dataHelper = new DatabaseHelper();
    private List<Food> foods = new ArrayList<>();
    private TextView fruit;
    private TextView vegetable;
    private TextView grain;
    private TextView protein;
    private TextView dairy;
    private String[] currentPicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflates activity_home
        setContentView(R.layout.activity_home);
        System.out.println("testing print");
        currentPicks = new String[5];
        //will probably need to be in recycler view in the future
        dataHelper.readFoods();

        foods = dataHelper.getFoods();

        fruit = findViewById(R.id.fruitText);
        grain = findViewById(R.id.grainText);
        vegetable = findViewById(R.id.vegetableText);
        protein = findViewById(R.id.proteinText);
        dairy = findViewById(R.id.dairyText);

        // add onClick() to Shuffle button
        Button shuffleButton = findViewById(R.id.shuffleButton);
        shuffleButton.setOnClickListener(new ShuffleClickHandler());
    }

    @Override
    public void onResume(){
        super.onResume();
        // repopulated with the 5 stored foods
        fruit.setText(currentPicks[0]);
        grain.setText(currentPicks[1]);
        vegetable.setText(currentPicks[2]);
        protein.setText(currentPicks[3]);
        dairy.setText(currentPicks[4]);
    }

    @Override
    public void onPause(){
        super.onPause();
        // store the 5 foods listed so far
    }

    @Override
    protected void onStart(){
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



    private class ShuffleClickHandler implements View.OnClickListener {
        // shuffle ingredients of the plate
        public void onClick(View view) {
            // change TextViews and texts to graphs later
            currentPicks[0] = getString(R.string.fruit_text, getRandomIngredient(foods, "Fruit"));
            fruit.setText(currentPicks[0]);
            currentPicks[1] = getString(R.string.grain_text, getRandomIngredient(foods, "Grain"));
            grain.setText(currentPicks[1]);
            currentPicks[2] = getString(R.string.vegetable_text, getRandomIngredient(foods, "Vegetable"));
            vegetable.setText(currentPicks[2]);
            currentPicks[3] = getString(R.string.protein_text, getRandomIngredient(foods, "Protein"));
            protein.setText(currentPicks[3]);
            currentPicks[4] =getString(R.string.dairy_text, getRandomIngredient(foods, "Dairy"));
            dairy.setText(currentPicks[4]);
        }
    }
}