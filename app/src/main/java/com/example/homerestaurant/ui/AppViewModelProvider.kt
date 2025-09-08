package com.example.homerestaurant.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.homerestaurant.HomeRestaurantApplication
import com.example.homerestaurant.ui.entree.EntreeViewModel
import com.example.homerestaurant.ui.food.FoodDetailsViewModel
import com.example.homerestaurant.ui.food.FoodEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for EntreeViewModel
        initializer {
            EntreeViewModel(
                homeRestaurantApplication().container.foodRepository
            )
        }
        // Initializer for FoodEntryViewModel
        initializer {
            FoodEntryViewModel(
                homeRestaurantApplication().container.foodRepository
            )
        }
        // Initializer for FoodDetailsViewModel
        initializer {
            FoodDetailsViewModel(
                this.createSavedStateHandle(),
                homeRestaurantApplication().container.foodRepository
            )
        }
    }
}

fun CreationExtras.homeRestaurantApplication(): HomeRestaurantApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HomeRestaurantApplication)