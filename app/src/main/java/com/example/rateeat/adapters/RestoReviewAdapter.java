package com.example.rateeat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rateeat.activities.SpecificRestaurantActivity;
import com.example.rateeat.models.RestoReview;
import com.example.rateeat.R;

import java.util.List;

public class RestoReviewAdapter extends RecyclerView.Adapter<RestoReviewAdapter.ViewHolder> {

    List<RestoReview> restoReviews;
    Context context;

    public RestoReviewAdapter(List<RestoReview> restoReviews, SpecificRestaurantActivity activity) {
        this.restoReviews = restoReviews;
        this.context = activity;

    }

    @NonNull
    @Override
    public RestoReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new RestoReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestoReview restoReview = restoReviews.get(position);
        holder.textViewName.setText(restoReview.getName());
        holder.textViewRating.setText(restoReview.getComment());

        int resourceId = context.getResources().getIdentifier(restoReview.getUserImageName(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.userImage.setImageResource(resourceId);
        } else {
            holder.userImage.setImageResource(R.drawable.default_image);
        }
    }

    @Override
    public int getItemCount() {
        return restoReviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView textViewName;
        TextView textViewRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImageView);
            textViewName = itemView.findViewById(R.id.nameTextView);
            textViewRating = itemView.findViewById(R.id.ratingTextView);

        }
    }

}
