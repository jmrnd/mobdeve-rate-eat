package com.example.rateeat.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.R;
import com.example.rateeat.adapters.UserReviewAdapter;
import com.example.rateeat.models.RestoReview;

import java.util.ArrayList;
import java.util.List;

public class UserReviewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserReviewAdapter adapter;
    private List<RestoReview> userReviews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_reviews);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDummyData();
        adapter = new UserReviewAdapter(userReviews, UserReviewsActivity.this);
        recyclerView.setAdapter(adapter);

    }


    //This loads dummy data for user reviews
    private void loadDummyData(){
        userReviews = new ArrayList<>();
        userReviews.add(new RestoReview("null", "Jollibee", "Saks lang", "May 2024","default_image"));
        userReviews.add(new RestoReview("null", "Mcdonalds", "yay", "January 2024", "default_image"));
        userReviews.add(new RestoReview("null", "KFC", "Sure", "November 2023","default_image"));

    }


}