package com.example.homerestaurant.data

// Represents a single ingredient required for a Food entry
data class Ingredient(
    val quantity: Int,
    val unit: String?,
    val name: String
)
