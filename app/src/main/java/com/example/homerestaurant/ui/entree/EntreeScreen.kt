package com.example.homerestaurant.ui.entree

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homerestaurant.HomeRestaurantTopAppBar
import com.example.homerestaurant.R
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination

object EntreeDestination : NavigationDestination {
    override val route = "entree"
    override val titleRes = R.string.entree
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntreeScreenListOnly(
    navigateToFoodEntry: () -> Unit,
    navigateToFoodDetails: (Food) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntreeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {

    val entreeUiState by viewModel.entreeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeRestaurantTopAppBar(
                title = stringResource(EntreeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToFoodEntry,
                modifier = Modifier
                    .padding(
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_item)
                )
            }
        }
    ) { innerPadding ->
        EntreeBody(
            entreeList = entreeUiState.foodList,
            onFoodClick = navigateToFoodDetails,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun EntreeBody(
    entreeList: List<Food>,
    onFoodClick: (Food) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

}