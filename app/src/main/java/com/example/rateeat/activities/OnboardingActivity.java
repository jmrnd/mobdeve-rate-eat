package com.example.rateeat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rateeat.R;

public class OnboardingActivity extends AppCompatActivity {

    private TextView skipTextView;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        skipTextView = findViewById(R.id.skipTextView);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        skipTextView.setOnClickListener(v -> goToMainActivity());
        loginButton.setOnClickListener(v -> startLoginActivity());
        signupButton.setOnClickListener(v -> startRegisterActivity());
    }

    private void goToMainActivity() {
        Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
        intent.putExtra("SKIP_LOGIN", true);
        startActivity(intent);
        finish();
    }

    private void startLoginActivity() {
        startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
    }

    private void startRegisterActivity() {
        startActivity(new Intent(OnboardingActivity.this, RegisterActivity.class));
    }
}