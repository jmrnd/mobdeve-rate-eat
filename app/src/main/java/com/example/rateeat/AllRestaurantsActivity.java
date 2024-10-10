package com.example.rateeat;
/*
    US: VIEW ALL RESTAURANT
    MAIN ACTIVITY: This class is the main activity for viewing all restaurants
 */
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllRestaurantsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_allrestaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RestaurantData[] RestaurantData = new RestaurantData[]{
                new RestaurantData("Jollibee", "★★★★★","5","Beside ManRes", R.drawable.jollibee),
                new RestaurantData("Jollibee", "★★★★★","5","Beside ManRes", R.drawable.jollibee),
                new RestaurantData("Jollibee", "★★★★★","5","Beside ManRes", R.drawable.jollibee),
                new RestaurantData("Jollibee", "★★★★★","5","Beside ManRes", R.drawable.jollibee),
                new RestaurantData("Jollibee", "★★★★★","5","Beside ManRes", R.drawable.jollibee),
        };

        RestaurantAdapter RestaurantAdapter = new RestaurantAdapter(RestaurantData,AllRestaurantsActivity.this);
        recyclerView.setAdapter(RestaurantAdapter);
    }
}