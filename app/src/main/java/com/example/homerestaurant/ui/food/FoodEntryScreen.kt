package com.example.homerestaurant.ui.food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homerestaurant.HomeRestaurantTopAppBar
import com.example.homerestaurant.R
import com.example.homerestaurant.data.FoodCategory
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object FoodEntryDestination : NavigationDestination {
    override val route = "food_entry"
    override val titleRes = R.string.food_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: FoodEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            HomeRestaurantTopAppBar(
                title = stringResource(FoodEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        FoodEntryBody(
            foodUiState = viewModel.foodUiState,
            onFoodValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveFood()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun FoodEntryBody(
    foodUiState: FoodUiState,
    onFoodValueChange: (FoodDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FoodEntryForm(
            foodDetails = foodUiState.foodDetails,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onFoodValueChange
        )
        Button(
            onClick = onSaveClick,
            enabled = foodUiState.isEntryValid,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_button))
        }
    }
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

@Preview
@Composable
fun FoodEntryBodyPreview() {
    FoodEntryBody(
        foodUiState = FoodUiState(foodDetails = FoodDetails(), isEntryValid = true),
        onFoodValueChange = { FoodDetails() },
        onSaveClick = { }
    )
}

@Preview
@Composable
fun FoodEntryScreenPreview() {
    FoodEntryScreen(
        navigateBack = {},
        onNavigateUp = {}
    )
}

// Placeholder to allow for proper compilation
data class FoodDetails(
    val name: String = "",
    val rating: String = "",
    val prepTime: String = "",
    val foodCategory: FoodCategory = FoodCategory.ENTREE
)

// Placeholder
data class FoodUiState (
    val foodDetails: FoodDetails,
    val isEntryValid: Boolean
)

// Placeholder
class FoodEntryViewModel {
    val foodUiState: FoodUiState = FoodUiState(foodDetails = FoodDetails(), isEntryValid = true)
    fun updateUiState(foodDetails: FoodDetails) {}
    fun saveFood() {}
}