package com.cse5236.whatsfordinnerapp.ui;

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
import android.widget.EditText;
import android.widget.Toast;

import com.cse5236.whatsfordinnerapp.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/*
 * change password by verifying current password
 */

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    public FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private EditText mOldPassword, mNewPassword, mConfirmPassword;
    private String mEmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_password, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mEmail = Objects.requireNonNull(mUser).getEmail();

        mOldPassword = v.findViewById(R.id.et_change_password_old_password);
        mNewPassword = v.findViewById(R.id.et_change_password_new_password);
        mConfirmPassword = v.findViewById(R.id.et_change_password_confirm_password);

        Button mConfirmButton = v.findViewById(R.id.btn_change_password_confirm);
        mConfirmButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_password_confirm) {
            String oldPassword = mOldPassword.getText().toString();
            String newPassword = mNewPassword.getText().toString();
            String confirmPassword = mConfirmPassword.getText().toString();
            changePassword(oldPassword, newPassword, confirmPassword);
        }
    }

    public boolean changePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.isEmpty()) {
            mOldPassword.setError("Please enter your current password");
            mOldPassword.requestFocus();
            return false;
        }
        if (newPassword.isEmpty()) {
            mNewPassword.setError("Please enter your new password");
            mNewPassword.requestFocus();
            return false;
        }
        if (confirmPassword.isEmpty()) {
            mConfirmPassword.setError("Please confirm your new password");
            mConfirmPassword.requestFocus();
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            mConfirmPassword.setError("Password confirmation doesn't match the password");
            mConfirmPassword.requestFocus();
            return false;
        }

        AuthCredential credential = EmailAuthProvider.getCredential(mEmail, oldPassword);
        mUser.reauthenticate(credential).addOnCompleteListener(reauth -> {
            if (reauth.isSuccessful()) {
                mUser.updatePassword(newPassword).addOnCompleteListener(update -> {
                    if (update.isSuccessful()) {
                        Toast.makeText(requireContext(), "Password updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(requireActivity(), SettingsActivity.class));
                    } else {
                        Toast.makeText(requireContext(), "Error, password not updated", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mOldPassword.setError("Password is not correct");
                mOldPassword.requestFocus();
            }
        });
        return true;
    }
}