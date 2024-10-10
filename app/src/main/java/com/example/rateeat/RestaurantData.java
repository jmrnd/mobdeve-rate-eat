package com.example.rateeat;


/*
    This class gets and sets the data of the restaurants
 */
public class RestaurantData {

    private String restaurantName;
    private String restaurantRating;
    private String restaurantReviews;
    private String restaurantLocation;
    private Integer restaurantImage;

    public RestaurantData(String restaurantName, String restaurantRating, String restaurantReviews, String restaurantLocation,Integer restaurantImage)     {
        this.restaurantName = restaurantName;
        this.restaurantRating = restaurantRating;
        this.restaurantImage = restaurantImage;
        this.restaurantReviews = restaurantReviews;
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(String restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    public String getRestaurantReviews() {
        return "(" + restaurantReviews + " reviews)";
    }

    public void setRestaurantReviews(String restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }

    public String getRestaurantLocation() {
        return "•    " + restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public Integer getRestaurantImage() {
        return restaurantImage;
    }

    public void setMovieImage(Integer movieImage) {
        this.restaurantImage = restaurantImage;
    }

}
