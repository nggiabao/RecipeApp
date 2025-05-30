package com.example.recipeapp.retrofitclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {


    companion object{
        private lateinit var retrofit: Retrofit
        private val BASE_URL = "" // The actual URL value is truncated in the image, assuming it's meant to be a base URL for API calls.
        val retrofitInstance:Retrofit
                     get() {
        if (retrofit == null){
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }}



}