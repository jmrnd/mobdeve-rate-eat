package com.example.rateeat.fragments;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rateeat.R;
import com.example.rateeat.adapters.ReviewAdapter;
import com.example.rateeat.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<Review> reviews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        // Handle window insets to accommodate system bars (like status bar and navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.userRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load dummy data and set adapter
        loadDummyData();
        adapter = new ReviewAdapter(reviews, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    // This loads dummy data for reviews
    private void loadDummyData() {
        reviews = new ArrayList<>();
        reviews.add(new Review("null", "Jollibee", "Bida ang saya", "05/21/24","jollibee"));
        reviews.add(new Review("null", "Mcdonalds", "I love it", "01/01/24", "mcdonalds"));
        reviews.add(new Review("null", "KFC", "Fingerlickin' good", "11/11/23","kfc"));
    }
}