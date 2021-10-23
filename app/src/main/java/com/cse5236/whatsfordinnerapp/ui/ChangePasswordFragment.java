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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * change password by verifying current password
 */

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private EditText mOldPassword, mNewPassword, mConfirmPassword;
    private Button mConfirmButton;
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
        mEmail = mUser.getEmail();

        mOldPassword = v.findViewById(R.id.et_change_password_old_password);
        mNewPassword = v.findViewById(R.id.et_change_password_new_password);
        mConfirmPassword = v.findViewById(R.id.et_change_password_confirm_password);

        mConfirmButton = v.findViewById(R.id.btn_change_password_confirm);
        mConfirmButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_password_confirm) {
            changePassword();
        }
    }

    private void changePassword() {
        String oldPassword = mOldPassword.getText().toString();
        String newPassword = mNewPassword.getText().toString();
        String confirmPassword = mConfirmPassword.getText().toString();

        if (oldPassword == null || oldPassword.isEmpty()) {
            mOldPassword.setError("Please enter your current password");
            mOldPassword.requestFocus();
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

        AuthCredential credential = EmailAuthProvider.getCredential(mEmail, oldPassword);
        mUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(requireContext(), "Password updated", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(requireActivity(), SettingsActivity.class));
                                    } else {
                                        Toast.makeText(requireContext(), "Error, password not updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            mOldPassword.setError("Password is not correct");
                            mOldPassword.requestFocus();
                        }
                    }
                });

    }
}