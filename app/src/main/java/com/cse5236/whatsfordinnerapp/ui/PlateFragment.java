package com.cse5236.whatsfordinnerapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cse5236.whatsfordinnerapp.DatabaseHelper;
import com.cse5236.whatsfordinnerapp.R;
import com.cse5236.whatsfordinnerapp.Utils;
import com.cse5236.whatsfordinnerapp.model.Food;
import com.cse5236.whatsfordinnerapp.model.User;
//import com.cse5236.whatsfordinnerapp.ui.AuthActivity;
import com.cse5236.whatsfordinnerapp.ui.SettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//shake imports
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Objects;

/**
 * Fragment for Plate screen
 * <p>
 * based on adamcchampion's LoginFragment
 */
public class PlateFragment extends Fragment implements View.OnClickListener {
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    //    shake variables end
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

    private Button mShuffleButton, mSettingsButton, mAboutButton, mDeleteAccount;

    //todo delete after checkpoint 4
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
//        shake
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plate, container, false);

        mAuth = FirebaseAuth.getInstance();

        Activity activity = requireActivity();
        // original home code
        fruit = v.findViewById(R.id.fruitText);
        grain = v.findViewById(R.id.grainText);
        vegetable = v.findViewById(R.id.vegetableText);
        protein = v.findViewById(R.id.proteinText);
        dairy = v.findViewById(R.id.dairyText);

        currentPicks = new String[5];

//        mShuffleButton = v.findViewById(R.id.shuffleButton);
//        mShuffleButton.setOnClickListener(this);

        mSettingsButton = v.findViewById(R.id.settingsButton);
        mSettingsButton.setOnClickListener(this);

        mAboutButton = v.findViewById(R.id.aboutButton);
        mAboutButton.setOnClickListener(this);

        mDeleteAccount = v.findViewById(R.id.delete_account_button);
        mDeleteAccount.setOnClickListener(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        fruit.setText(R.string.onStartText);
        grain.setText("");
        vegetable.setText("");
        protein.setText("");
        dairy.setText("");
    }

    @Override
    public void onResume() {
//        shake sensor
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
//        shake sensor
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();
        switch (viewId) {
            case R.id.shuffleButton:
                shuffle();
                break;
            case R.id.settingsButton:
                startActivity(new Intent(activity, SettingsActivity.class));
                break; //test to see if you can hit back after logging out to break this
            case R.id.aboutButton:
                startActivity(new Intent(activity, AboutActivity.class));
                break;
            case R.id.delete_account_button:
                //todo delete after checkpoint 4
                DatabaseReference Fb = databaseHelper.getDatabase().getReference("Users");
                final String userId = mAuth.getCurrentUser().getUid();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Fb.child(userId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Activity activity = requireActivity();
                                        Toast.makeText(activity, "User Deleted", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(activity, AuthActivity.class));
                                        activity.finish();
                                    } else {
                                        Toast.makeText(activity, "Error Deleting User", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(activity, "Error Deleting User", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case R.id.fruitText:
//                find the name of the fruit currently displayed
                String fruitName = currentPicks[0];
//                look up the database for the fruit with that name
                foods = databaseHelper.getFoods();
                String fruitLink = Utils.getFoodLink(foods, fruitName);
                Intent openBrowserLink = new Intent(Intent.ACTION_VIEW);
                openBrowserLink.setData(Uri.parse(fruitLink));
                startActivity(openBrowserLink);
                break;
            default:
                break;
        }

    }

    private void shuffle() {
        foods = databaseHelper.getFoods();

        currentPicks[0] = getString(R.string.fruit_text, Utils.getRandomIngredient(foods, "Fruit"));
        fruit.setText(currentPicks[0]);
        currentPicks[1] = getString(R.string.grain_text, Utils.getRandomIngredient(foods, "Grain"));
        grain.setText(currentPicks[1]);
        currentPicks[2] = getString(R.string.vegetable_text, Utils.getRandomIngredient(foods, "Vegetable"));
        vegetable.setText(currentPicks[2]);
        currentPicks[3] = getString(R.string.protein_text, Utils.getRandomIngredient(foods, "Protein"));
        protein.setText(currentPicks[3]);
        currentPicks[4] = getString(R.string.dairy_text, Utils.getRandomIngredient(foods, "Dairy"));
        dairy.setText(currentPicks[4]);
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
//                Toast.makeText(getActivity().getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
                shuffle();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
