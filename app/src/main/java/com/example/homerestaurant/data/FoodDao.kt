package com.example.homerestaurant.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// Data access object for Room database queries
@Dao
interface FoodDao {

    // get flow of food list
    @Query("SELECT * from food ORDER BY name ASC")
    fun getAllFoods(): Flow<List<Food>>

    // get flow of single food
    @Query("SELECT * from food WHERE id = :id")
    fun getFood(id: Int): Flow<Food>

    // insert food into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food)

    // update food in database
    @Update
    suspend fun update(food: Food)

    // delete food from database
    @Delete
    suspend fun delete(food: Food)

}