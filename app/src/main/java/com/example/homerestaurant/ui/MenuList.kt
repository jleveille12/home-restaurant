package com.example.homerestaurant.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homerestaurant.data.Food

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

}