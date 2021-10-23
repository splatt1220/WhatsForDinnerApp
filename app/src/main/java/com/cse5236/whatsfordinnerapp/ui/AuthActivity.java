package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class AuthActivity extends SingleFragmentActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment(){return new AuthFragment();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
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