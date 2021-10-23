package com.cse5236.whatsfordinnerapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cse5236.whatsfordinnerapp.R;

/*
 * view of setting options
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private Button mChangePasswordButton, mUpdateDislikesButton, mLogOutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mChangePasswordButton = v.findViewById(R.id.btn_settings_change_password);
        mChangePasswordButton.setOnClickListener(this);

        mUpdateDislikesButton = v.findViewById(R.id.btn_settings_update_dislikes);
        mUpdateDislikesButton.setOnClickListener(this);

        mLogOutButton = v.findViewById(R.id.btn_settings_log_out);
        mLogOutButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Activity activity = requireActivity();
        switch (view.getId()) {
            case R.id.btn_settings_change_password:
                startActivity(new Intent(activity, ChangePasswordActivity.class));
                break;
            case R.id.btn_settings_update_dislikes:
                // todo
                break;
            case R.id.btn_settings_log_out:
                // todo
                break;
            default:
                Log.d(TAG, "No matched button. ");
                break;
        }

    }

}