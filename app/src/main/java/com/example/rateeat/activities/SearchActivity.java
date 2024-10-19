package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rateeat.R;
import com.example.rateeat.adapters.RestaurantAdapter;
import com.example.rateeat.models.Restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> allRestaurants = new ArrayList<>();
    private List<Restaurant> filteredRestaurants = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.searchRecyclerView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(filteredRestaurants, this);
        recyclerView.setAdapter(adapter);

        loadAllRestaurants();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterRestaurants(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRestaurants(newText);
                return false;
            }
        });

        setupBottomNavigation();
    }

    private void loadAllRestaurants() {
        Restaurant.getAllRestaurants(task -> {
            if (task.isSuccessful()) {
                allRestaurants.clear();
                for (Restaurant restaurant : task.getResult().toObjects(Restaurant.class)) {
                    allRestaurants.add(restaurant);
                }
                filteredRestaurants.addAll(allRestaurants);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void filterRestaurants(String query) {
        filteredRestaurants.clear();
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredRestaurants.add(restaurant);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                navigateToMainActivity(false);
                return true;
            } else if (id == R.id.profile) {
                navigateToMainActivity(true);
                return true;
            }
            return false;
        });
    }

    private void navigateToMainActivity(boolean openProfile) {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("OPEN_PROFILE", openProfile);
        startActivity(intent);
        finish();
    }
}