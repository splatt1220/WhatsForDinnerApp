package com.cse5236.whatsfordinnerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class SettingsActivity extends SingleFragmentActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment(){return new SettingsFragment();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
}