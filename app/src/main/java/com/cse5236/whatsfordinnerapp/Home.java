package com.cse5236.whatsfordinnerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Ingredients ingredients = new Ingredients();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // get data from Foods.json file
        ingredients.getDataFromJson("Foods.json", getApplicationContext());

        // add onClick() to Shuffle button
        Button shuffleButton = findViewById(R.id.shuffleButton);
        shuffleButton.setOnClickListener(new ShuffleClickHandler());
    }

    private class ShuffleClickHandler implements View.OnClickListener {
        // shuffle ingredients of the plate
        public void onClick(View view) {
            // change TextViews and texts to graphs later
            TextView fruit = findViewById(R.id.fruitText);
            TextView grain = findViewById(R.id.grainText);
            TextView vegetable = findViewById(R.id.vegetableText);
            TextView protein = findViewById(R.id.proteinText);
            TextView dairy = findViewById(R.id.dairyText);

            fruit.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Fruit)));
            grain.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Grain)));
            vegetable.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Vegetable)));
            protein.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Protein)));
            dairy.setText(getString(R.string.fruit_text, ingredients.getRandomIngredient(Ingredients.Category.Dairy)));
        }
    }
}