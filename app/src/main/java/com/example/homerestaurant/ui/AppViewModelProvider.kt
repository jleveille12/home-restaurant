package com.example.homerestaurant.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.homerestaurant.HomeRestaurantApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

    }
}

fun CreationExtras.homeRestaurantApplication(): HomeRestaurantApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HomeRestaurantApplication)