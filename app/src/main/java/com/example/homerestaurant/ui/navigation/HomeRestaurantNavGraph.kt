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
import com.example.homerestaurant.ui.food.FoodEntryDestination
import com.example.homerestaurant.ui.food.FoodEntryScreen
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
                navigateToFoodEntry = { navController.navigate(FoodEntryDestination.route) },
                navigateToFoodDetails = { navController.navigate("${FoodDetailsDestination.route}/${it}") }
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
        composable(route = FoodEntryDestination.route) {
            FoodEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = FoodDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(FoodDetailsDestination.foodIdArg) {
                type = NavType.IntType
            })
        ) {
            FoodDetailsScreen(
                navigateToEditFood = { /*FIXME*/ },
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}