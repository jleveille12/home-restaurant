package com.example.homerestaurant.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homerestaurant.R
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.data.FoodCategory

// lazy column showing list of menu items
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

// ui card to represent a single food recipe entry
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
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = food.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Alarm,
                        contentDescription = "Alarm",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = stringResource(R.string.prep_time, food.prepTime))
                }
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            StarRating(
                rating = food.rating,
                )
        }
    }
}

// composable to show food rating represented by filled in star icons
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
                contentDescription = null,
                tint = if (i <= rating) Color.Blue else Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
fun MenuListPreview() {
    MenuList(
        foodList = listOf(
            Food(1, FoodCategory.ENTREE, "Burger", 10, 3),
            Food(2, FoodCategory.ENTREE, "Chicken", 15, 2),
            Food(3, FoodCategory.ENTREE, "Steak", 20, 5),
            Food(4, FoodCategory.ENTREE, "Spaghetti", 25, 4)
        ),
        onFoodClick = { food -> },
        contentPadding = PaddingValues(0.dp)
    )
}

@Preview(showBackground = false)
@Composable
fun MenuItemPreview() {
    MenuItem(
        Food(1, FoodCategory.ENTREE, "Burger", 10, 3, imageUri = null, ingredients = null)
    )
}

@Preview(showBackground = true)
@Composable
fun StarRatingPreview() {
    StarRating(4)
}