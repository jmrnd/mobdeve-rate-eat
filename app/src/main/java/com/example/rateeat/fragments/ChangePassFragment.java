package com.example.rateeat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rateeat.R;

public class ChangePassFragment extends Fragment {

    private Button saveButton;
    private TextView forgotPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);

        saveButton = view.findViewById(R.id.saveButton);
        forgotPass = view.findViewById(R.id.forgotPass);

        saveButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "New password saved", Toast.LENGTH_SHORT).show();

            // Go back to the previous fragment in the back stack
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        forgotPass.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Redirect to forgot pass", Toast.LENGTH_SHORT).show();

            // Go back to the previous fragment in the back stack
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}