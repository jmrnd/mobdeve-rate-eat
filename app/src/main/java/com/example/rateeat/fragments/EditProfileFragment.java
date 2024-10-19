package com.example.rateeat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rateeat.R;

public class EditProfileFragment extends Fragment {

    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Successfully saved profile changes", Toast.LENGTH_SHORT).show();

            // Go back to the previous fragment in the back stack
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}