package com.example.rateeat.models;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RestoReview {
    private String id;
    private String name;
    private String ratingText;
    private String userImage;

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RestoReview(String id, String name, String ratingText, String userImage){
        this.id = id;
        this.name = name;
        this.ratingText = ratingText;
        this.userImage = userImage;
    }
    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getComment(){return ratingText;}
    public void setComment(String comment){this.ratingText = comment;}

    public String getUserImageName(){return userImage;}
    public void setUserImageName(String userImage){this.userImage = userImage;}

    public static void addReview(RestoReview restoReview, OnCompleteListener<Void> listener) {
        Map<String, Object> restoReviewMap = new HashMap<>();
        restoReviewMap.put("name", restoReview.getName());
        restoReviewMap.put("description", restoReview.getComment());
        restoReviewMap.put("location", restoReview.getUserImageName());

        db.collection("restaurants")
                .document()
                .set(restoReviewMap)
                .addOnCompleteListener(listener);
    }
}

