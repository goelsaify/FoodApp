package com.saify.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FoodDetails extends AppCompatActivity {

    String name, rating, price, imageUrl;
    ImageView itemImage;
    TextView itemName, itemPrice, itemRating;
    RatingBar itemRatingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        rating = intent.getStringExtra("rating");
        price  = intent.getStringExtra("price");
        imageUrl = intent.getStringExtra("item_image");

        itemImage = findViewById(R.id.item_image);
        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemRating = findViewById(R.id.item_rating);
        itemRatingBar = findViewById(R.id.item_ratingBar);

        Glide.with(getApplicationContext()).load(imageUrl).into(itemImage);
        itemName.setText(name);
        itemPrice.setText(price);
        itemRating.setText(rating);
        itemRatingBar.setRating(Float.parseFloat(rating));
    }
}