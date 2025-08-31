package com.example.homerestaurant.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * from food ORDER BY name ASC")
    fun getAllFoods(): Flow<List<Food>>

    @Query("SELECT * from food WHERE id = :id")
    fun getFood(id: Int): Flow<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food)

    @Update
    suspend fun update(food: Food)

    @Delete
    suspend fun delete(food: Food)

}