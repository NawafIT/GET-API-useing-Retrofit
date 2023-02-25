package com.api.apiresturant.mvvm

import com.api.apiresturant.api.MealsWebServer
import com.api.apiresturant.response.MealsCategoriesResponse

class MealsRepository(private val webServer: MealsWebServer = MealsWebServer()) {
    suspend fun getMeals(): MealsCategoriesResponse {
        return webServer.getMeals()
    }
}