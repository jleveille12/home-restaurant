package com.example.homerestaurant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homerestaurant.ui.dessert.DessertDestination
import com.example.homerestaurant.ui.dessert.DessertScreen
import com.example.homerestaurant.ui.entree.EntreeDestination
import com.example.homerestaurant.ui.entree.EntreeScreen
import com.example.homerestaurant.ui.side.SideDestination
import com.example.homerestaurant.ui.side.SideScreen

@Composable
fun HomeRestaurantNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = EntreeDestination.route,
        modifier = modifier
    ) {
        composable(route = EntreeDestination.route) {
            EntreeScreen(
                // TODO: FIXME -callback functions are simply placeholders
                navigateToFoodEntry = { navController.navigate(EntreeDestination.route) },
                navigateToFoodDetails = { navController.navigate(EntreeDestination.route) }
            )
        }
        composable(route = SideDestination.route) {
            SideScreen(

            )
        }
        composable(route = DessertDestination.route) {
            DessertScreen(

            )
        }
    }
}