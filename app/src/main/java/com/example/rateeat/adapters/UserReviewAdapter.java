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
import com.example.rateeat.activities.UserReviewsActivity;
import com.example.rateeat.models.RestoReview;

import java.util.List;

public class UserReviewAdapter extends RecyclerView.Adapter<UserReviewAdapter.ViewHolder> {

    List<RestoReview> userReviews;
    Context context;

    public UserReviewAdapter(List<RestoReview> userReviews, UserReviewsActivity activity) {
        this.userReviews = userReviews;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestoReview restoReview = userReviews.get(position);
        holder.textViewName.setText(restoReview.getName());
        holder.textViewRating.setText(restoReview.getComment());
        holder.reviewDate.setText(restoReview.getDate());

        int resourceId = context.getResources().getIdentifier(restoReview.getUserImageName(), "drawable", context.getPackageName());
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
