package com.example.homerestaurant.ui.navigation

interface NavigationDestination {

    // Unique name to define the path
    val route: String

    // String resource id for the page title
    val titleRes: Int

}