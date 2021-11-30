package com.cse5236.whatsfordinnerapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse5236.whatsfordinnerapp.R;
import com.google.firebase.auth.FirebaseAuth;

/*
 * change password by sending an email
 */

public class ForgetPasswordFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private FirebaseAuth mAuth;

    private EditText mEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forget_password, container, false);

        mAuth = FirebaseAuth.getInstance();

        mEmail = v.findViewById(R.id.et_forget_password_email);

        Button mConfirm = v.findViewById(R.id.btn_forget_password_confirm);
        mConfirm.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_forget_password_confirm) {
            String email = mEmail.getText().toString();
            forgetPassword(email);
        }
    }

    public boolean forgetPassword(String email) {

        if (email.isEmpty()) {
            mEmail.setError("Email is not valid");
            mEmail.requestFocus();
            return false;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (!isAdded()) return;
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Check your email to reset your password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error, please try again", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

}