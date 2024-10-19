package com.example.rateeat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.activities.RestaurantActivity;
import com.example.rateeat.models.Review;
import com.example.rateeat.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    List<Review> reviews;
    Context context;

    public ReviewAdapter(List<Review> reviews, RestaurantActivity activity) {
        this.reviews = reviews;
        this.context = activity;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewName.setText(review.getName());
        holder.textViewRating.setText(review.getComment());
        holder.reviewDate.setText(review.getDate());

        int resourceId = context.getResources().getIdentifier(review.getUserImageName(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.userImage.setImageResource(resourceId);
        } else {
            holder.userImage.setImageResource(R.drawable.default_image);
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView textViewName;
        TextView textViewRating;
        TextView reviewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImageView);
            textViewName = itemView.findViewById(R.id.nameTextView);
            textViewRating = itemView.findViewById(R.id.ratingTextView);
            reviewDate = itemView.findViewById(R.id.reviewDate);
        }
    }

}
