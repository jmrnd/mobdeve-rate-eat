package com.example.rateeat.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rateeat.R;
import com.example.rateeat.adapters.RestaurantAdapter;
import com.example.rateeat.models.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> allRestaurants = new ArrayList<>();
    private List<Restaurant> filteredRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Search Restaurants");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.searchRecyclerView);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}