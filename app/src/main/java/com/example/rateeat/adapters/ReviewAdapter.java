package com.example.rateeat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.R;
import com.example.rateeat.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    List<Review> userReviews;
    Context context;

    public ReviewAdapter(List<Review> userReviews, Context context) {
        this.userReviews = userReviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = userReviews.get(position);
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
        return userReviews.size();
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
