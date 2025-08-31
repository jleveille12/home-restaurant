package com.example.homerestaurant.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a single food or "dish" entry in the database
@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val prepTime: Int,
    val rating: Int,
    val imageUri: Uri,
    val ingredients: List<Ingredient>
    )
