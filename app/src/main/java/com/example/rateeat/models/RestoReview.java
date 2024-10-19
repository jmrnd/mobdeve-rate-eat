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
    private String date;

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RestoReview(String id, String name, String ratingText, String date, String userImage){
        this.id = id;
        this.name = name;
        this.ratingText = ratingText;
        this.date = date;
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

    public String getDate(){return this.date;}
    public void setDate(String date){this.date = date;}

    public static void addReview(RestoReview restoReview, OnCompleteListener<Void> listener) {
        Map<String, Object> restoReviewMap = new HashMap<>();
        restoReviewMap.put("name", restoReview.getName());
        restoReviewMap.put("description", restoReview.getComment());
        restoReviewMap.put("image", restoReview.getUserImageName());
        restoReviewMap.put("date", restoReview.getDate());


        db.collection("restaurants")
                .document()
                .set(restoReviewMap)
                .addOnCompleteListener(listener);
    }
}

