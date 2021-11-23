package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public class ChangePasswordActivity extends SingleFragmentActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment(){return new ChangePasswordFragment();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
}