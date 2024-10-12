package com.example.rateeat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.R;
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
        holder.nameTextView.setText(restaurant.getName());
        holder.ratingBar.setRating((float) restaurant.getAverageRating());
        holder.ratingTextView.setText(String.format("%.1f", restaurant.getAverageRating()));
        holder.reviewCountTextView.setText(String.format("(%d reviews)", restaurant.getReviewCount()));
        holder.locationTextView.setText(restaurant.getLocation());

        int resourceId = context.getResources().getIdentifier(restaurant.getImageResourceName(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.imageView.setImageResource(resourceId);
        } else {
            holder.imageView.setImageResource(R.drawable.default_image);
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

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