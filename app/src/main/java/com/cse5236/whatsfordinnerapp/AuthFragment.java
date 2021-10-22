package com.cse5236.whatsfordinnerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AuthFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    //private scope info
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);
        Activity activity = requireActivity();

        mEmail = v.findViewById(R.id.emailField);
        mPassword = v.findViewById(R.id.passwordField);

        mLoginButton = v.findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(this);
        mRegister = v.findViewById(R.id.registerButton);
        mRegister.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if(mLoginButton.getId() == viewId) {
                //TODO submit info to firebase
        } else if (mRegister.getId() == viewId) {
            Activity activity = requireActivity();
            startActivity(new Intent(activity, RegisterActivity.class));
//            activity.finish();
                //TODO sends to register page
        }
    }

}