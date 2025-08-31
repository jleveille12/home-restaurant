package com.example.homerestaurant.data

import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    fun getAllFoodsStream(): Flow<List<Food>>
    fun getFoodStream(id: Int): Flow<Food?>
    suspend fun insertFood(food: Food)
    suspend fun deleteFood(food: Food)
    suspend fun updateFood(food: Food)

}