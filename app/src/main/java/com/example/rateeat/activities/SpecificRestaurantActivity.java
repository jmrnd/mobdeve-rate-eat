package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rateeat.R;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SpecificRestaurantActivity extends AppCompatActivity {

    ImageView restaurantImageView;
    TextView nameTextView;
    RatingBar ratingBar;
    TextView ratingTextView;
    TextView reviewCountTextView;
    TextView dotTextView;
    TextView locationTextView;
    TextView restaurantDescTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_specific_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        i.putExtra("name",restaurant.getName());
//        i.putExtra("description",restaurant.getDescription());
//        i.putExtra("location",restaurant.getLocation());
//        i.putExtra("averageRating", restaurant.getAverageRating());
//        i.putExtra("reviewCount", restaurant.getReviewCount());
//        i.putExtra("image", restaurant.getImageResourceName());

        restaurantImageView = findViewById(R.id.restaurantImg);
        nameTextView = findViewById(R.id.restaurantNameTextView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingTextView = findViewById(R.id.ratingTextView);
        reviewCountTextView = findViewById(R.id.reviewCountTextView);
        dotTextView = findViewById(R.id.dotTextView);
        locationTextView = findViewById(R.id.locationTextView);
        restaurantDescTextView = findViewById(R.id.restaurantDescTextView);

        Intent i = getIntent();

        int imageResId = getResources().getIdentifier("image", "drawable", getPackageName());
        restaurantImageView.setImageResource(imageResId);
        nameTextView.setText(i.getStringExtra("name"));
        ratingBar.setRating((float) i.getDoubleExtra("averageRating", 0.0));
        ratingTextView.setText(i.getStringExtra("averageRating"));
        reviewCountTextView.setText(i.getStringExtra("reviewCount"));
        locationTextView.setText(i.getStringExtra("location"));
        restaurantDescTextView.setText(i.getStringExtra("description"));


    }



}