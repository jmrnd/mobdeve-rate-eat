package com.example.rateeat.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.rateeat.R;
import com.example.rateeat.adapters.RestaurantAdapter;
import com.example.rateeat.models.Restaurant;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurants = new ArrayList<>(); // Initialize here
    private FirebaseFirestore db;
    private ListenerRegistration restaurantListener;
    private boolean isRefreshing = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView and other views
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RestaurantAdapter(restaurants, getContext());
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        swipeRefreshLayout.setOnRefreshListener(this::refreshContent);

        checkAndLoadRestaurants();

        return view;
    }

    private void checkAndLoadRestaurants() {
        db.collection("restaurants").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().isEmpty()) {
                    // Collection is empty, use dummy data
                    loadDummyData();
                } else {
                    loadRestaurants();
                }
            } else {
                onRestaurantsError("Error checking restaurants: " + task.getException().getMessage());            }
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
                    Log.e("HomeFragment", "Error adding dummy restaurant: " + restaurant.getName(), task.getException());                }
            });
        }

        loadRestaurants();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (restaurantListener != null) {
            restaurantListener.remove();
        }
    }

    public void refreshContent() {
        if (!isRefreshing) {
            isRefreshing = true;
            recyclerView.smoothScrollToPosition(0);
            swipeRefreshLayout.setRefreshing(true);
            loadRestaurants();
        }
    }

    private void loadRestaurants() {
        if (restaurantListener != null) {
            restaurantListener.remove();
        }

        restaurantListener = Restaurant.getRestaurantsRealtime(
                this::onRestaurantsLoaded,
                e -> onRestaurantsError("Error loading restaurants: " + e.getMessage())
        );
    }

    private void onRestaurantsLoaded(List<Restaurant> newRestaurants) {
        if (isAdded() && getContext() != null) {
            restaurants.clear();
            restaurants.addAll(newRestaurants);
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
            isRefreshing = false;
        }
    }

    private void onRestaurantsError(String errorMessage) {
        if (isAdded() && getContext() != null) {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            swipeRefreshLayout.setRefreshing(false);
            isRefreshing = false;
        }
    }
}
