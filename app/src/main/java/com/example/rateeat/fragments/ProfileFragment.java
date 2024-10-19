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
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.rateeat.R;
import com.example.rateeat.activities.ChangePasswordActivity;
import com.example.rateeat.activities.EditProfileActivity;
import com.example.rateeat.activities.LoginActivity;
import com.example.rateeat.activities.UserReviewsActivity;

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

        onClickMyReviews();
        onClickEditProfile();
        onClickChangePassword();
        onClickLogOut();

        return view;
    }

    public void onClickMyReviews() {
        myReviews.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), UserReviewsActivity.class);
            startActivity(i);
        });
    }

    public void onClickEditProfile() {
        editProfile.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(i);
        });
    }

    public void onClickChangePassword() {
        changePassword.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
            startActivity(i);
        });
    }

    public void onClickLogOut() {
        logOut.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), LoginActivity.class);

            //clears all tasks
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

    }




}
