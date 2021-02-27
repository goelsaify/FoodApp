package com.saify.foodapp;

import android.content.Context;
import android.content.Intent;
import android.gesture.GestureLibraries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saify.foodapp.model.Popular;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<Popular> popularList;

    public PopularAdapter(Context context, List<Popular> popularList){
        this.context = context;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.popularName.setText(popularList.get(position).getName());
        Glide.with(context)
                .load(popularList.get(position).getImageUrl())
                .into(holder.popularImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name", popularList.get(position).getName());
                i.putExtra("price", popularList.get(position).getPrice());
                i.putExtra("rating",popularList.get(position).getRating());
                i.putExtra("item_image", popularList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView popularImage;
        TextView popularName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularImage = itemView.findViewById(R.id.popular_image);
            popularName = itemView.findViewById(R.id.popular_name);
        }
    }
}
