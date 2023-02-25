package com.api.apiresturant.mvvm

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.api.apiresturant.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealsRepository = MealsRepository()) : ViewModel() {
     val mealsCategories: MutableState<List<MealsResponse>> =
        mutableStateOf(emptyList())

    init {

        viewModelScope.launch(Dispatchers.IO) {

            mealsCategories.value = getMeals()

        }

    }


    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories


    }
}