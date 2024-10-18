package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rateeat.R;
import com.example.rateeat.adapters.RestaurantAdapter;
import com.example.rateeat.adapters.RestoReviewAdapter;
import com.example.rateeat.models.Restaurant;
import com.example.rateeat.models.RestoReview;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificRestaurantActivity extends AppCompatActivity {

    ImageView restaurantImageView;
    TextView nameTextView;
    RatingBar ratingBar;
    TextView ratingTextView;
    TextView reviewCountTextView;
    TextView dotTextView;
    TextView locationTextView;
    TextView restaurantDescTextView;

    private RecyclerView recyclerView;
    private RestoReviewAdapter adapter;
    private List<RestoReview> restoReviews = new ArrayList<>();


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

        setRestaurantDetails();

        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDummyData();
        adapter = new RestoReviewAdapter(restoReviews, SpecificRestaurantActivity.this);
        recyclerView.setAdapter(adapter);


    }


    //This loads the description of the restaurants
    public void setRestaurantDetails(){
        //This finds the xml elements
        restaurantImageView = findViewById(R.id.restaurantImg);
        nameTextView = findViewById(R.id.restaurantNameTextView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingTextView = findViewById(R.id.ratingTextView);
        reviewCountTextView = findViewById(R.id.reviewCountTextView);
        dotTextView = findViewById(R.id.dotTextView);
        locationTextView = findViewById(R.id.locationTextView);
        restaurantDescTextView = findViewById(R.id.restaurantDescTextView);

        Intent i = getIntent();


        //This sets the text onto the activity
        String imagename = i.getStringExtra("image");
        int imageResId = getResources().getIdentifier(imagename, "drawable", getPackageName());
        restaurantImageView.setImageResource(imageResId);
        nameTextView.setText(i.getStringExtra("name"));
        ratingBar.setRating((float) i.getDoubleExtra("averageRating", 0.0));
        ratingTextView.setText(String.valueOf(i.getDoubleExtra("averageRating", 0.0)));
        reviewCountTextView.setText(String.format("(%d reviews)", i.getIntExtra("reviewCount", -1)));
        locationTextView.setText(i.getStringExtra("location"));
        restaurantDescTextView.setText(i.getStringExtra("description"));
    }

    //This loads dummy data for user reviews
    private void loadDummyData(){
        restoReviews = new ArrayList<>();
        restoReviews.add(new RestoReview("null", "Jeka", "Saks lang", "default_image"));
        restoReviews.add(new RestoReview("null", "Abby", "yay", "default_image"));
        restoReviews.add(new RestoReview("null", "Jamar", "Sure", "default_image"));

    }


}