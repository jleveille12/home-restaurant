package com.example.homerestaurant.ui.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homerestaurant.data.FoodRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FoodEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val foodRepository: FoodRepository
) : ViewModel() {

    var foodUiState by mutableStateOf(FoodUiState())
        private set

    private val foodId: Int = checkNotNull(savedStateHandle[FoodEditDestination.foodIdArg])

    init {
        viewModelScope.launch {
            foodUiState = foodRepository.getFoodStream(foodId)
                .filterNotNull()
                .first()
                .toFoodUiState(true)
        }
    }

    suspend fun updateFood() {
        if (validateInput(foodUiState.foodDetails)) {
            foodRepository.updateFood(foodUiState.foodDetails.toFood())
        }
    }

    fun updateUiState(foodDetails: FoodDetails) {
        foodUiState =
            FoodUiState(foodDetails = foodDetails, isEntryValid = validateInput(foodDetails))
    }

    private fun validateInput(uiState: FoodDetails = foodUiState.foodDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && rating.isNotBlank() && prepTime.isNotBlank()
        }
    }

}