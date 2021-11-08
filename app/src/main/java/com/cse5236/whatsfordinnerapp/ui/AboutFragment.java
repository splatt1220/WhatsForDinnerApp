package com.cse5236.whatsfordinnerapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cse5236.whatsfordinnerapp.R;

public class AboutFragment extends Fragment{
    private final String TAG = getClass().getSimpleName();

    //private scope info
    private TextView mAboutText;
    private String mText =
            "Shake the phone and you will get a new plate. " +
            "In Setting menu you can change your password and update your disliked foods. " +
            "\n\nCAUTION: QUALITY OF MEAL IS NOT GUARANTEED! " +
            "\n\n\nBy: Zahur Elmi, Sarah Platt, Baihua Yang";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        mAboutText = v.findViewById(R.id.tv_about);
        mAboutText.setText(mText);

        return v;
    }
}