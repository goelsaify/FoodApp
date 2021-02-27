package com.saify.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saify.foodapp.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    private Context context;
    private List<Allmenu> allmenuList;

    public AllMenuAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_items, parent,false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, int position) {

        Glide.with(context)
                .load(allmenuList.get(position).getImageUrl())
                .into(holder.popularImage);

        holder.popularName.setText(allmenuList.get(position).getName());
        holder.popularNote.setText(allmenuList.get(position).getNote());
        holder.popularRating.setText(allmenuList.get(position).getRating());
        holder.popularTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.popularCharges.setText(allmenuList.get(position).getDeliveryCharges());
        holder.popularPrice.setText(allmenuList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", allmenuList.get(position).getPrice());
                i.putExtra("rating",allmenuList.get(position).getRating());
                i.putExtra("item_image", allmenuList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView popularName, popularNote, popularRating, popularTime, popularCharges, popularPrice;
        ImageView popularImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            popularName = itemView.findViewById(R.id.all_menu_name);
            popularNote = itemView.findViewById(R.id.all_menu_note);
            popularRating = itemView.findViewById(R.id.all_menu_rating);
            popularTime = itemView.findViewById(R.id.all_menu_deliverytime);
            popularCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            popularPrice = itemView.findViewById(R.id.all_menu_price);
            popularImage = itemView.findViewById(R.id.all_menu_image);



        }
    }
}
