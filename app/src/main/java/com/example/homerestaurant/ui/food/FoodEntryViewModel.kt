package com.example.homerestaurant.ui.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.homerestaurant.data.Food
import com.example.homerestaurant.data.FoodCategory
import com.example.homerestaurant.data.FoodRepository

class FoodEntryViewModel(private val foodRepository: FoodRepository): ViewModel() {

    var foodUiState by mutableStateOf(FoodUiState())
        private set

    fun updateUiState(foodDetails: FoodDetails) {
        foodUiState =
            FoodUiState(foodDetails = foodDetails, isEntryValid = validateInput(foodDetails))
    }

    suspend fun saveFood() {
        if (validateInput()) {
            foodRepository.insertFood(foodUiState.foodDetails.toFood())
        }
    }

    private fun validateInput(uiState: FoodDetails = foodUiState.foodDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && rating.isNotBlank() && prepTime.isNotBlank()
        }
    }

}

data class FoodUiState(
    val foodDetails: FoodDetails = FoodDetails(),
    val isEntryValid: Boolean = false
)

data class FoodDetails(
    val name: String = "",
    val rating: String = "",
    val prepTime: String = "",
    val foodCategory: FoodCategory = FoodCategory.ENTREE
)

fun FoodDetails.toFood(): Food = Food(
    name = name,
    rating = rating.toIntOrNull() ?: 0,
    prepTime = prepTime.toIntOrNull() ?: 0,
    category = foodCategory
)