package com.example.homerestaurant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.homerestaurant.ui.dessert.DessertDestination
import com.example.homerestaurant.ui.dessert.DessertScreen
import com.example.homerestaurant.ui.entree.EntreeDestination
import com.example.homerestaurant.ui.entree.EntreeScreen
import com.example.homerestaurant.ui.food.FoodDetailsDestination
import com.example.homerestaurant.ui.food.FoodDetailsScreen
import com.example.homerestaurant.ui.food.FoodEditDestination
import com.example.homerestaurant.ui.food.FoodEditScreen
import com.example.homerestaurant.ui.food.FoodEntryDestination
import com.example.homerestaurant.ui.food.FoodEntryScreen
import com.example.homerestaurant.ui.side.SideDestination
import com.example.homerestaurant.ui.side.SideScreen

// navHost starts at Entree screen
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
        // Entree screen
        composable(route = EntreeDestination.route) {
            EntreeScreen(
                navigateToFoodEntry = { navController.navigate(FoodEntryDestination.route) },
                navigateToFoodDetails = { navController.navigate("${FoodDetailsDestination.route}/${it}") }
            )
        }
        // Side screen
        composable(route = SideDestination.route) {
            SideScreen(

            )
        }
        // Dessert screen
        composable(route = DessertDestination.route) {
            DessertScreen(

            )
        }
        // Food Entry screen
        composable(route = FoodEntryDestination.route) {
            FoodEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        // Food Details screen
        composable(
            route = FoodDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(FoodDetailsDestination.foodIdArg) {
                type = NavType.IntType
            })
        ) {
            FoodDetailsScreen(
                navigateToEditFood = { navController.navigate("${FoodEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        // Food Edit screen
        composable(
            route = FoodEditDestination.routeWithArgs,
            arguments = listOf(navArgument(FoodEditDestination.foodIdArg) {
                type = NavType.IntType
            })
        ) {
            FoodEditScreen(
                navigateBack = { navController.navigateUp() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}