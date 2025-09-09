package com.example.homerestaurant.ui.food

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homerestaurant.R
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination

object FoodEditDestination : NavigationDestination {
    override val route = "food_edit"
    override val titleRes = R.string.edit_food
    const val foodIdArg = "foodId"
    val routeWithArgs = "$route/{$foodIdArg}"
}

@Composable
fun FoodEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FoodEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    

}