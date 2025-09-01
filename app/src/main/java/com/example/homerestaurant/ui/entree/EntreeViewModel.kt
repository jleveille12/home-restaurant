package com.example.homerestaurant.ui.entree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.data.FoodCategory
import com.example.homerestaurant.data.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EntreeViewModel(foodRepository: FoodRepository) : ViewModel() {

    val entreeUiState: StateFlow<EntreeUiState> =
        foodRepository.getAllFoodsStream().map { foodList ->
            val entrees = foodList.filter { it.category == FoodCategory.ENTREE }
            EntreeUiState(entrees)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EntreeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class EntreeUiState(val foodList: List<Food> = listOf())