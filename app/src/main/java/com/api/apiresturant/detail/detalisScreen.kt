package com.api.apiresturant.detail


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.api.apiresturant.R
import com.api.apiresturant.mealscreen.MealCategory
import com.api.apiresturant.mvvm.MealsViewModel

import com.api.apiresturant.response.MealsResponse
import kotlin.math.min

@Composable
fun MealDetail(name: String?, img: String?) {
//    var profile by remember { mutableStateOf(Profile.Normal) }
//    val transition = updateTransition(targetState = profile, label = "")

//    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
//    val widthSize by transition.animateDp(targetValueByState = { it.border }, label = "")
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    val imageSize by animateDpAsState(targetValue = max(100.dp, 170.dp * offset))

    Surface {
        Column {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.LightGray),
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(img)
                                .crossfade(1000)
                                .transformations(CircleCropTransformation())
                                .build(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.photo),
                            contentDescription = "placeholder",
                            modifier = Modifier
                                .size(imageSize)
                                .clip(CircleShape)
                                .padding(4.dp)

                        )
                    }
                    Text(
                        text = name!!,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContent = (0..100).map { it.toString() }
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(dummyContent) {
                    Text(text = it, modifier = Modifier.padding(32.dp))
                }
            }


        }
    }

}

enum class Profile(val color: Color, val size: Dp, val border: Dp) {
    Normal(color = Color.Gray, size = 100.dp, border = 4.dp),
    Expanded(color = Color.Green, size = 200.dp, border = 16.dp)
}