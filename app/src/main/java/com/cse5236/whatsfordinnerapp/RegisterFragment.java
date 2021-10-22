package com.cse5236.whatsfordinnerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse5236.whatsfordinnerapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private EditText mEmail;
    private EditText mName;
    private EditText mPassword;
    private Button mRegister;

    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();

        mEmail = v.findViewById(R.id.TextEmail);
        mName = v.findViewById(R.id.TextName);
        mPassword = v.findViewById(R.id.TextPassword);

        mRegister = v.findViewById(R.id.registeringButton);
        mRegister.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registeringButton:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email = mEmail.getText().toString().trim();
        String name = mName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //TODO make sure name is not empty
        //TODO make sure password >6 characters
        if(password.isEmpty()){
            mPassword.setError("Please enter a password");
            mPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please give a valid email");
            mEmail.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Activity activity = requireActivity();
                        if(task.isSuccessful()){
                            User user = new User(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
//                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Activity activity = requireActivity();
                                    if(task.isSuccessful()){
                                        Toast.makeText(activity, "User created successfully", Toast.LENGTH_LONG).show();
                                        Log.d(TAG, "Successful user creation" );

                                        //redirect to login layout
                                        activity.finish();

                                    } else {
                                        Toast.makeText(activity, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                        Log.d(TAG, "Failed to create user 1" );
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(activity, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Failed to create user 2" );
                        }
                    }
                });
    }
}