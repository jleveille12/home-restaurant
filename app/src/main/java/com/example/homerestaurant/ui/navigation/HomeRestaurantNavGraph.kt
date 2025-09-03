package com.example.homerestaurant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homerestaurant.ui.entree.EntreeDestination

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