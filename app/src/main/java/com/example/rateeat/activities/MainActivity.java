package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rateeat.R;
import com.example.rateeat.databinding.ActivityMainBinding;
import com.example.rateeat.fragments.HomeFragment;
import com.example.rateeat.fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private ImageView searchIcon;
    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("All Restaurants");

        searchIcon = binding.toolbar.findViewById(R.id.searchIcon);
        searchIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check if user skipped login
        boolean skippedLogin = getIntent().getBooleanExtra("SKIP_LOGIN", false);
        isLoggedIn = !skippedLogin;

        initializeFragments();
        setupBottomNavigation();

        if (getIntent().getBooleanExtra("OPEN_PROFILE", false)) {
            showFragment(profileFragment);
            binding.bottomNavigationView.setSelectedItemId(R.id.profile);
        } else {
            showFragment(homeFragment);
        }
    }

    private void initializeFragments() {
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, homeFragment, "home")
                .add(R.id.frameLayout, profileFragment, "profile")
                .hide(profileFragment)
                .commit();
    }

    private void setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                if (binding.bottomNavigationView.getSelectedItemId() == id) {
                    if (homeFragment != null) {
                        homeFragment.refreshContent();
                    }
                } else {
                    clearBackStack();  // Clear the back stack before switching to Home
                    showFragment(homeFragment);
                    getSupportActionBar().setTitle("All Restaurants");
                    searchIcon.setVisibility(View.VISIBLE);
                }
            } else if (id == R.id.profile) {
                clearBackStack();  // Clear the back stack before switching to Profile
                showFragment(profileFragment);
                getSupportActionBar().setTitle("Profile");
                searchIcon.setVisibility(View.GONE);
            }

            return true;
        });
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(profileFragment)
                .show(fragment)
                .commit();
    }

    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}