package com.example.rateeat.models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userId, String email, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Authentication methods

    public static void registerUser(String email, String password, String firstName, String lastName, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            User newUser = new User(firebaseUser.getUid(), email, firstName, lastName);
                            saveUserToFirestore(newUser);
                        }
                    }
                    listener.onComplete(task);
                });
    }

    public static void loginUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public static void forgotPassword(String email, OnCompleteListener<Void> listener) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(listener);
    }

    public static void logoutUser() {
        mAuth.signOut();
    }

    public static FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    private static void saveUserToFirestore(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("firstName", user.getFirstName());
        userMap.put("lastName", user.getLastName());

        db.collection("users").document(user.getUserId())
                .set(userMap)
                .addOnSuccessListener(aVoid -> {
                    // User data saved successfully
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                });
    }
}