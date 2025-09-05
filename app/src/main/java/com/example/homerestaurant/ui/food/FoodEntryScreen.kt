package com.example.homerestaurant.ui.food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homerestaurant.R
import com.example.homerestaurant.data.FoodCategory
import com.example.homerestaurant.ui.navigation.NavigationDestination

object FoodEntryDestination : NavigationDestination {
    override val route = "food_entry"
    override val titleRes = R.string.food_entry_title
}

@Composable
fun FoodEntryScreen() {

}

@Composable
fun FoodEntryBody() {

}

@Composable
fun FoodEntryForm(
    foodDetails: FoodDetails,
    modifier: Modifier = Modifier,
    onValueChange: (FoodDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = foodDetails.name,
            onValueChange = { onValueChange(foodDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.food_name_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = foodDetails.rating,
            onValueChange = { onValueChange(foodDetails.copy(rating = it)) },
            label = { Text(stringResource(R.string.food_rating_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = foodDetails.prepTime,
            onValueChange = { onValueChange(foodDetails.copy(prepTime = it)) },
            label = { Text(stringResource(R.string.prep_time_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        FoodCategorySelector(
            selectedCategory = foodDetails.foodCategory,
            onCategorySelected = { onValueChange(foodDetails.copy(foodCategory = it)) }
        )
    }
}

@Composable
fun FoodCategorySelector(
    selectedCategory: FoodCategory,
    onCategorySelected: (FoodCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FoodCategory.values().forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category) }
            ) {
                RadioButton(
                    selected = (category == selectedCategory),
                    onClick = { onCategorySelected(category) }
                )
                Text(
                    text = category.name.lowercase().replaceFirstChar { it.uppercase() }
                )
            }
        }
    }
}

@Preview
@Composable
fun FoodCategorySelectorPreview() {
    FoodCategorySelector(
        selectedCategory = FoodCategory.ENTREE,
        onCategorySelected = { }
    )
}

@Preview
@Composable
fun FoodEntryFormPreview() {
    FoodEntryForm(
        foodDetails = FoodDetails(),
    )
}

// Placeholder to allow for proper compilation
data class FoodDetails(
    val name: String = "",
    val rating: String = "",
    val prepTime: String = "",
    val foodCategory: FoodCategory = FoodCategory.ENTREE
)