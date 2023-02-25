package com.api.apiresturant.api

import com.api.apiresturant.response.MealsCategoriesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MealsWebServer {
    private lateinit var api: MealsAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MealsAPI::class.java)
    }

    suspend fun getMeals(): MealsCategoriesResponse {
        return api.getMeals()
    }

    interface MealsAPI {
        // endpoint we want
        @GET("categories.php")
            suspend fun getMeals(): MealsCategoriesResponse
    }
}