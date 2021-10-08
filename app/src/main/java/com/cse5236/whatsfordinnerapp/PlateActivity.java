package com.cse5236.whatsfordinnerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.fragment.app.Fragment;

import com.cse5236.whatsfordinnerapp.ui.SingleFragmentActivity;

/**
 * Activity for Plate interaction
 *
 * Based on adamcchampion's LoginActivity.java
 */
public class PlateActivity extends SingleFragmentActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment(){ return new PlateFragment(); }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.d(TAG,"onPause");
    }

}
