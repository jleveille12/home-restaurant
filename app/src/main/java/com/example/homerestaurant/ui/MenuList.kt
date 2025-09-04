package com.example.homerestaurant.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.data.FoodCategory

@Composable
fun MenuList(
    foodList: List<Food>,
    onFoodClick: (Food) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = foodList, key = { it.id }) { food ->
            MenuItem(
                food = food,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onFoodClick(food) }
            )
        }
    }
}

@Composable
private fun MenuItem(
    food: Food, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
            ) {

            }
            StarRating(food.rating)
        }
    }
}

@Composable
private fun StarRating(
    rating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 5
) {
    Row(
        modifier = modifier
    ) {
        for (i in 1..maxRating) {
            val icon = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(
        Food(1, FoodCategory.ENTREE, "Burger", 10, 5, imageUri = null, ingredients = null)
    )
}