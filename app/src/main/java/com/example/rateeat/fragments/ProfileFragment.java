package com.example.rateeat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rateeat.R;
import com.example.rateeat.activities.LoginActivity;

public class ProfileFragment extends Fragment {


    private LinearLayout myReviews;
    private LinearLayout editProfile;
    private LinearLayout changePassword;
    private LinearLayout logOut;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        myReviews = view.findViewById(R.id.ratedRestos);
        editProfile = view.findViewById(R.id.editProfile);
        changePassword = view.findViewById(R.id.changePass);
        logOut = view.findViewById(R.id.logOut);

        myReviews.setOnClickListener(v -> {
            Fragment reviews = new ReviewsFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frameLayout, reviews);
            fm.addToBackStack(null);  // Add this line to enable back navigation
            fm.commit();
        });

        editProfile.setOnClickListener(v -> {
            Fragment editProfile = new EditProfileFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frameLayout, editProfile);
            fm.addToBackStack(null);  // Add this line to enable back navigation
            fm.commit();
        });

        changePassword.setOnClickListener(v -> {
            Fragment changePass = new ChangePassFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frameLayout, changePass);
            fm.addToBackStack(null);  // Add this line to enable back navigation
            fm.commit();
        });

        logOut.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), LoginActivity.class);

            // clears all tasks
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

        return view;
    }
}
