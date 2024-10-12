package com.example.rateeat.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.R;
import com.example.rateeat.adapters.RestaurantAdapter;
import com.example.rateeat.models.Restaurant;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllRestaurantsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurants;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_restaurants);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurants = new ArrayList<>();
        adapter = new RestaurantAdapter(restaurants, this);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        checkAndLoadRestaurants();
    }

    private void checkAndLoadRestaurants() {
        db.collection("restaurants").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().isEmpty()) {
                    // Collection is empty, use dummy data
                    // NOTE: Remove this in the future
                    loadDummyData();
                } else {
                    loadRestaurants();
                }
            } else {
                Toast.makeText(this, "Error checking restaurants: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadDummyData() {
        List<Restaurant> dummyRestaurants = Arrays.asList(
                new Restaurant(null, "Jollibee", "Fast food restaurant", "Beside ManRes", 4.5, 100, "jollibee"),
                new Restaurant(null, "McDonald's", "Fast food chain", "Near Main Gate", 4.2, 150, "mcdonalds"),
                new Restaurant(null, "KFC", "Fried chicken restaurant", "Inside Campus", 4.0, 80, "kfc"),
                new Restaurant(null, "Pizza Hut", "Pizza restaurant", "Opposite Library", 4.3, 90, "pizzahut"),
                new Restaurant(null, "Subway", "Sandwich chain", "Student Center", 4.1, 70, "subway"),
                new Restaurant(null, "Sample Restaurant", "Meow", "Meow Meow", 3.5, 25, "default_image")

        );

        for (Restaurant restaurant : dummyRestaurants) {
            Restaurant.addRestaurant(restaurant, task -> {
                if (!task.isSuccessful()) {
                    Log.e("AllRestaurantsActivity", "Error adding dummy restaurant: " + restaurant.getName(), task.getException());
                }
            });
        }

        // For debugging
//        for (Restaurant restaurant : dummyRestaurants) {
//            Restaurant.addRestaurant(restaurant, task -> {
//                if (task.isSuccessful()) {
//                    Toast.makeText(this, "Added dummy restaurant: " + restaurant.getName(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Error adding dummy restaurant: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }

        loadRestaurants();
    }

    private void loadRestaurants() {
        Restaurant.getAllRestaurants(task -> {
            if (task.isSuccessful()) {
                restaurants.clear();
                for (Restaurant restaurant : task.getResult().toObjects(Restaurant.class)) {
                    restaurants.add(restaurant);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error loading restaurants: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}