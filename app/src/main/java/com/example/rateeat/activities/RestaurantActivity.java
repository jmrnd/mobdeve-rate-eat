package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rateeat.R;
import com.example.rateeat.adapters.ReviewAdapter;
import com.example.rateeat.models.Review;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ImageView restaurantImageView;
    TextView nameTextView;
    RatingBar ratingBar;
    TextView ratingTextView;
    TextView reviewCountTextView;
    TextView dotTextView;
    TextView locationTextView;
    TextView restaurantDescTextView;

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<Review> reviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        restaurantImageView = findViewById(R.id.restaurantImg);
        nameTextView = findViewById(R.id.restaurantNameTextView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingTextView = findViewById(R.id.ratingTextView);
        reviewCountTextView = findViewById(R.id.reviewCountTextView);
        dotTextView = findViewById(R.id.dotTextView);
        locationTextView = findViewById(R.id.locationTextView);
        restaurantDescTextView = findViewById(R.id.restaurantDescTextView);

        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setRestaurantDetails();

        loadDummyData();
        adapter = new ReviewAdapter(reviews, RestaurantActivity.this);
        recyclerView.setAdapter(adapter);
    }

    //This loads the description of the restaurants
    public void setRestaurantDetails() {
        Intent i = getIntent();
        if (i == null) {
            // Handle the case where the intent is null
            return;
        }

        String restaurantName = i.getStringExtra("name");
        if (restaurantName != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(restaurantName);
            }
            if (nameTextView != null) {
                nameTextView.setText(restaurantName);
            }
        }

        String imagename = i.getStringExtra("image");
        if (imagename != null) {
            int imageResId = getResources().getIdentifier(imagename, "drawable", getPackageName());
            if (imageResId != 0 && restaurantImageView != null) {
                restaurantImageView.setImageResource(imageResId);
            } else {
                // Set a default image or handle the error
                restaurantImageView.setImageResource(R.drawable.default_image);
            }
        }

        double rating = i.getDoubleExtra("averageRating", 0.0);
        if (ratingBar != null) {
            ratingBar.setRating((float) rating);
        }
        if (ratingTextView != null) {
            ratingTextView.setText(String.valueOf(rating));
        }

        int reviewCount = i.getIntExtra("reviewCount", -1);
        if (reviewCountTextView != null) {
            reviewCountTextView.setText(String.format("(%d reviews)", reviewCount));
        }

        String location = i.getStringExtra("location");
        if (locationTextView != null && location != null) {
            locationTextView.setText(location);
        }

        String description = i.getStringExtra("description");
        if (restaurantDescTextView != null && description != null) {
            restaurantDescTextView.setText(description);
        }
    }

    //This loads dummy data for user reviews
    private void loadDummyData(){
        reviews = new ArrayList<>();
        reviews.add(new Review("null", "Jeka", "Great food and atmosphere! The pasta was cooked to perfection and the service was top-notch. Highly recommend the tiramisu for dessert.", "default_image", "10/29/24"));
        reviews.add(new Review("null", "Abby", "Average experience. The food was okay, but nothing special. Service was a bit slow during peak hours. Might give it another try in the future.", "default_image", "10/29/23"));
        reviews.add(new Review("null", "Jamar", "Absolutely fantastic! Best sushi I've had in years. The chef's special roll was a delightful surprise. Will definitely be coming back soon!", "default_image", "10/29/22"));
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}