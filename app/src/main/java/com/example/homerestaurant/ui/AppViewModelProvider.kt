package com.example.homerestaurant.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.homerestaurant.HomeRestaurantApplication
import com.example.homerestaurant.ui.entree.EntreeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for EntreeViewModel
        initializer {
            EntreeViewModel(
                homeRestaurantApplication().container.foodRepository
            )
        }
    }
}

fun CreationExtras.homeRestaurantApplication(): HomeRestaurantApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HomeRestaurantApplication)