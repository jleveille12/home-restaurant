package com.example.homerestaurant.ui.food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homerestaurant.data.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FoodDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val foodId: Int = checkNotNull(savedStateHandle[FoodDetailsDestination.foodIdArg])

    val uiState: StateFlow<FoodDetailsUiState> =
        foodRepository.getFoodStream(foodId)
            .filterNotNull()
            .map {
                FoodDetailsUiState(foodDetails = it.toFoodDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FoodDetailsUiState()
            )

    // Delete food from the database
    suspend fun deleteFood() {
        foodRepository.deleteFood(uiState.value.foodDetails.toFood())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FoodDetailsUiState(
    val foodDetails: FoodDetails = FoodDetails()
)