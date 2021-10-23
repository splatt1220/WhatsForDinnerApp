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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/*
 * change password by sending an email
 */

public class ForgetPasswordFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private FirebaseAuth mAuth;

    private EditText mEmail, mNewPassword, mConfirmPassword;
    private Button mConfirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forget_password, container, false);

        mAuth = FirebaseAuth.getInstance();

        mEmail =  v.findViewById(R.id.et_forget_password_email);
        mNewPassword = v.findViewById(R.id.et_forget_password_new_password);
        mConfirmPassword = v.findViewById(R.id.et_forget_password_confirm_password);

        mConfirmButton = v.findViewById(R.id.btn_forget_password_confirm);
        mConfirmButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_forget_password_confirm) {
            forgetPassword();
        }
    }

    private void forgetPassword() {
        String email = mEmail.getText().toString();
        String newPassword = mNewPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();

        if (email == null || email.isEmpty()) {
            mEmail.setError("Email is not valid");
            mEmail.requestFocus();
            return;
        }
        if (newPassword == null || newPassword.isEmpty()) {
            mNewPassword.setError("Please enter your new password");
            mNewPassword.requestFocus();
            return;
        }
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            mConfirmPassword.setError("Please confirm your new password");
            mConfirmPassword.requestFocus();
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            mConfirmPassword.setError("Password confirmation doesn't match the password");
            mConfirmPassword.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(requireContext(), "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(requireContext(), "Error, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}