package com.api.apiresturant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.*


import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.api.apiresturant.detail.MealDetail

import com.api.apiresturant.mealscreen.MealsScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodsApp()
        }

    }


}


@Composable
private fun FoodsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Menu") {
        composable(route = "Menu") {
            MealsScreen(navController)


        }
        composable(
            route = "MenuDetail?name={name}/img={img}",
            arguments = listOf(
                navArgument(name = "name")
                {
                    type = NavType.StringType
                },

                navArgument(name = "img")
                {
                    type = NavType.StringType
                }
            )

        ) {
            MealDetail(
                it.arguments?.getString("name"),
                it.arguments?.getString("img"),
            )
        }
    }

}
