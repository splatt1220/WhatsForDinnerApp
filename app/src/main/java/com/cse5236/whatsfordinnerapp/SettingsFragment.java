package com.cse5236.whatsfordinnerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private Button mLogout;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mLogout = v.findViewById(R.id.logout_button);
        mLogout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        return v;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Activity activity = requireActivity();
        switch (viewId) {
            case R.id.logout_button:
                mAuth.signOut();
                startActivity(new Intent(activity, AuthActivity.class));
                activity.finish();
                break;
            default:
                break;
        }
    }
}