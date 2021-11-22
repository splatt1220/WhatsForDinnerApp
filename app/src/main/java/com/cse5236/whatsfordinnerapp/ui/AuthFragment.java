package com.cse5236.whatsfordinnerapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse5236.whatsfordinnerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    //private scope info
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mRegister;
    private Button mForgetPasswordButton;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);

        mEmail = v.findViewById(R.id.emailField);
        mPassword = v.findViewById(R.id.passwordField);

        mLoginButton = v.findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(this);
        mRegister = v.findViewById(R.id.registerButton);
        mRegister.setOnClickListener(this);
        mForgetPasswordButton = v.findViewById(R.id.forgetPasswordButton);
        mForgetPasswordButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        return v;
    }

    @Override
    public void onClick(View view) {
        Activity activity = requireActivity();
        switch (view.getId()) {
            case R.id.loginButton:
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                userLogin(email, password);
                break;
            case R.id.registerButton:
                startActivity(new Intent(activity, RegisterActivity.class));
                break;
            case R.id.forgetPasswordButton:
                startActivity(new Intent(activity, ForgetPasswordActivity.class));
                break;
            default:
                break;
        }
    }

    public boolean userLogin(String email, String password) {

        if (email.isEmpty()) {
            mEmail.setError("Email is required");
            mEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please enter a valid email");
            mEmail.requestFocus();
            return false;
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("Password is not valid");
            mPassword.requestFocus();
            return false;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Activity activity = requireActivity();
                if (task.isSuccessful()) {
                    //redirect user to profile
                    startActivity(new Intent(activity, PlateActivity.class));
                    activity.finish();
                } else {
                    Toast.makeText(activity, "Failed to login, please check input", Toast.LENGTH_LONG).show();
                }
            }
        });
        return true;
    }

}