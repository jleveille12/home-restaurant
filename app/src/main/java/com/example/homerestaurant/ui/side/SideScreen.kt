package com.example.homerestaurant.ui.side

import androidx.compose.runtime.Composable
import com.example.homerestaurant.R
import com.example.homerestaurant.ui.navigation.NavigationDestination

object SideDestination : NavigationDestination {
    override val route = "side"
    override val titleRes = R.string.side
}

@Composable
fun SideScreen() {

}