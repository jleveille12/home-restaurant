package com.example.homerestaurant.ui.food

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.homerestaurant.R
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination

object FoodDetailsDestination : NavigationDestination {
    override val route = "food_details"
    override val titleRes = R.string.food_details
    const val foodIdArg = "foodId"
    val routeWithArgs = "$route/{$foodIdArg}"
}

@Composable
fun FoodDetailsScreen(
    navigateToEditFood: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

}