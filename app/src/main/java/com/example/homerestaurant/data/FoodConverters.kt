package com.example.homerestaurant.data

import android.net.Uri
import androidx.room.TypeConverter

class FoodConverters {

    // --- FoodCategory enum ---
    @TypeConverter
    fun fromCategory(category: FoodCategory): String = category.name

    @TypeConverter
    fun toCategory(name: String): FoodCategory = FoodCategory.valueOf(name)

    // --- Image Uri ---
    @TypeConverter
    fun fromUri(uri: Uri?): String? = uri?.toString()

    @TypeConverter
    fun toUri(uriString: String?): Uri? = uriString?.let { Uri.parse(it) }

    // --- Ingredients List<String> ---
    @TypeConverter
    fun fromListString(list: List<String>?): String? = list?.joinToString(",")

    @TypeConverter
    fun toListString(listString: String?): List<String>? = listString?.split(",")
}