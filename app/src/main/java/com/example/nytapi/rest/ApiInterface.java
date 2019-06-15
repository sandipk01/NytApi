package com.example.nytapi.rest;



import com.example.nytapi.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("arts.json")
    Call<Example> getTopRatedMovies(@Query("api-key") String apiKey);
}
