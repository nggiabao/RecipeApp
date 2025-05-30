package com.example.recipeapp.interfaces


import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.Meal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET(value = "/categories.php")
    fun getCategoryList(): Call<Category>

    @GET(value = "/filter.php")
    fun getMealList(@Query(value = "c") category: String): Call<Meal>
}



