package com.saify.foodapp.retrofit;

import com.saify.foodapp.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();
}
