package com.api.apiresturant.mealscreen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.api.apiresturant.R
import com.api.apiresturant.mvvm.MealsViewModel
import com.api.apiresturant.response.MealsResponse

@Composable
fun MealsScreen(navController: NavController) {
    val viewModel: MealsViewModel = viewModel()
    val meals = viewModel.mealsCategories.value

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealCategory(meal, navController)
        }
    }
}

@Composable
fun MealCategory(meal: MealsResponse, navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {

                navController.navigate(route = "MenuDetail?name=${meal.name}/img=${meal.imageUrl}")
            }

    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.imageUrl)
                    .transformations(CircleCropTransformation())
                    .crossfade(1000)
                    .build(),
                placeholder = painterResource(R.drawable.photo),
                contentDescription = "placeholder",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)

            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = meal.description,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 8 else 2,
                        textAlign = TextAlign.Start
                    )
                }
            }
            Icon(
                imageVector = if (isExpanded)
                    Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand row",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded) Alignment.Bottom else Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )


        }
    }

}
