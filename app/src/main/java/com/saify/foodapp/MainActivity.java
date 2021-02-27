package com.saify.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.saify.foodapp.model.Allmenu;
import com.saify.foodapp.model.FoodData;
import com.saify.foodapp.model.Popular;
import com.saify.foodapp.model.Recommended;
import com.saify.foodapp.retrofit.ApiService;
import com.saify.foodapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;
    RecyclerView recyclerViewPopular, recyclerViewRecommended, recyclerViewAllMenu;
    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<FoodData>> call = apiService.getAllData();

        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodDataList = response.body();

                getPopularData(foodDataList.get(0).getPopular());

                getRecommendedData(foodDataList.get(0).getRecommended());

                getAllMenuData(foodDataList.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Server is not responding", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getPopularData(List<Popular> popularData) {
        recyclerViewPopular = findViewById(R.id.popular_recycler);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopular.setHasFixedSize(true);

        popularAdapter = new PopularAdapter(this, popularData);
        recyclerViewPopular.setAdapter(popularAdapter);
    }

    public void getRecommendedData(List<Recommended> recommendedList){

        recyclerViewRecommended = findViewById(R.id.recommended_recycler);
        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecommended.setHasFixedSize(true);

        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        recyclerViewRecommended.setAdapter(recommendedAdapter);
    }

    public void getAllMenuData(List<Allmenu> allmenuList){

        recyclerViewAllMenu = findViewById(R.id.all_menu_recycler);
        recyclerViewAllMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerViewAllMenu.setHasFixedSize(true);

        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        recyclerViewAllMenu.setAdapter(allMenuAdapter);
    }
}
