package com.example.rateeat;


/*
    US: VIEW ALL RESTAURANT
    This class sets the data onto the holder/recyclerview
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    RestaurantData[] RestaurantData;
    Context context;

    public RestaurantAdapter(RestaurantData[] RestaurantData,AllRestaurantsActivity activity) {
        this.RestaurantData = RestaurantData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaraunt_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RestaurantData myMovieDataList = RestaurantData[position];
        holder.textViewName.setText(myMovieDataList.getRestaurantName());
        holder.textViewRating.setText(myMovieDataList.getRestaurantRating());
        holder.textViewReviews.setText(myMovieDataList.getRestaurantReviews());
        holder.textViewLocation.setText(myMovieDataList.getRestaurantLocation());
        holder.restaurantImage.setImageResource(myMovieDataList.getRestaurantImage());
    }

    @Override
    public int getItemCount() {
        return RestaurantData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurantImage;
        TextView textViewName;
        TextView textViewRating;
        TextView textViewReviews;
        TextView textViewLocation;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewRating = itemView.findViewById(R.id.textstars);
            textViewReviews = itemView.findViewById(R.id.textreviews);
            textViewLocation = itemView.findViewById(R.id.textlocation);


        }
    }

}
