package com.example.rateeat.models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private String id;
    private String name;
    private String description;
    private String location;
    private double averageRating;
    private int reviewCount;
    private String imageResourceName;

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Restaurant() { }

    public Restaurant(String id, String name, String description, String location, double averageRating, int reviewCount, String imageResourceName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.imageResourceName = imageResourceName;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getImageResourceName() {
        return imageResourceName;
    }

    public void setImageResourceName(String imageResourceName) {
        this.imageResourceName = imageResourceName;
    }

    public static void getAllRestaurants(OnCompleteListener<QuerySnapshot> listener) {
        FirebaseFirestore.getInstance()
                .collection("restaurants")
                .get()
                .addOnCompleteListener(listener);
    }


    public static void getRestaurantById(String restaurantId, OnCompleteListener<DocumentSnapshot> listener) {
        db.collection("restaurants")
                .document(restaurantId)
                .get()
                .addOnCompleteListener(listener);
    }

    public static void addRestaurant(Restaurant restaurant, OnCompleteListener<Void> listener) {
        Map<String, Object> restaurantMap = new HashMap<>();
        restaurantMap.put("name", restaurant.getName());
        restaurantMap.put("description", restaurant.getDescription());
        restaurantMap.put("location", restaurant.getLocation());
        restaurantMap.put("averageRating", restaurant.getAverageRating());
        restaurantMap.put("reviewCount", restaurant.getReviewCount());
        restaurantMap.put("imageResourceName", restaurant.getImageResourceName());  // Changed from imageUrl to imageResourceName

        db.collection("restaurants")
                .document()
                .set(restaurantMap)
                .addOnCompleteListener(listener);
    }

    public static void updateRestaurant(Restaurant restaurant, OnCompleteListener<Void> listener) {
        Map<String, Object> restaurantMap = new HashMap<>();
        restaurantMap.put("name", restaurant.getName());
        restaurantMap.put("description", restaurant.getDescription());
        restaurantMap.put("location", restaurant.getLocation());
        restaurantMap.put("averageRating", restaurant.getAverageRating());
        restaurantMap.put("reviewCount", restaurant.getReviewCount());
        restaurantMap.put("imageUrl", restaurant.getImageResourceName());

        db.collection("restaurants")
                .document(restaurant.getId())
                .update(restaurantMap)
                .addOnCompleteListener(listener);
    }

    public static void deleteRestaurant(String restaurantId, OnCompleteListener<Void> listener) {
        db.collection("restaurants")
                .document(restaurantId)
                .delete()
                .addOnCompleteListener(listener);
    }

    public static ListenerRegistration getRestaurantsRealtime(OnSuccessListener<List<Restaurant>> onSuccess, OnFailureListener onFailure) {
        return FirebaseFirestore.getInstance()
                .collection("restaurants")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        onFailure.onFailure(error);
                        return;
                    }

                    List<Restaurant> restaurants = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        restaurants.add(doc.toObject(Restaurant.class));
                    }
                    onSuccess.onSuccess(restaurants);
                });
    }
}