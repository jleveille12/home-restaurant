package com.example.homerestaurant.ui.entree

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homerestaurant.R
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination

object EntreeDestination : NavigationDestination {
    override val route = "entree"
    override val titleRes = R.string.entree
}

@Composable
fun EntreeScreenListOnly(
    navigateToFoodDetails: (Food) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntreeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {

}