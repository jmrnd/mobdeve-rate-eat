package com.example.rateeat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.R;
import com.example.rateeat.activities.SpecificRestaurantActivity;
import com.example.rateeat.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurants;
    private Context context;

    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        int resourceId = context.getResources().getIdentifier(restaurant.getImageResourceName(), "drawable", context.getPackageName());
        holder.nameTextView.setText(restaurant.getName());
        holder.ratingBar.setRating((float) restaurant.getAverageRating());
        holder.ratingTextView.setText(String.format("%.1f", restaurant.getAverageRating()));
        holder.reviewCountTextView.setText(String.format("(%d reviews)", restaurant.getReviewCount()));
        holder.locationTextView.setText(restaurant.getLocation());
        if (resourceId != 0) {
            holder.imageView.setImageResource(resourceId);
        } else {
            holder.imageView.setImageResource(R.drawable.default_image);
        }

        holder.itemView.setOnClickListener((View v) -> {
            Toast.makeText(context, restaurant.getName(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, SpecificRestaurantActivity.class);

            //These are the data that will be passed from MainActivity to SpecificRestaurantActivity
            i.putExtra("name",restaurant.getName());
            i.putExtra("description",restaurant.getDescription());
            i.putExtra("location",restaurant.getLocation());
            i.putExtra("averageRating", restaurant.getAverageRating());
            i.putExtra("reviewCount", restaurant.getReviewCount());
            i.putExtra("image", restaurant.getImageResourceName());

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    //This is where we match the xml elements to the java variables for the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        RatingBar ratingBar;
        TextView ratingTextView;
        TextView reviewCountTextView;
        TextView dotTextView;
        TextView locationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.restaurantImageView);
            nameTextView = itemView.findViewById(R.id.restaurantNameTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            reviewCountTextView = itemView.findViewById(R.id.reviewCountTextView);
            dotTextView = itemView.findViewById(R.id.dotTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
        }
    }
}