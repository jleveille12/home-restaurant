package com.example.homerestaurant.data

import android.net.Uri
import androidx.room.TypeConverter

class FoodConverters {

    // --- FoodCategory enum ---

    // category to string
    @TypeConverter
    fun fromCategory(category: FoodCategory): String = category.name

    // string to category
    @TypeConverter
    fun toCategory(name: String): FoodCategory = FoodCategory.valueOf(name)

    // --- Image Uri ---

    // uri to string
    @TypeConverter
    fun fromUri(uri: Uri?): String? = uri?.toString()

    // string to uri
    @TypeConverter
    fun toUri(uriString: String?): Uri? = uriString?.let { Uri.parse(it) }

    // --- Ingredients List<String> ---

    // list to single string
    @TypeConverter
    fun fromListString(list: List<String>?): String? = list?.joinToString(",")

    // single string to list
    @TypeConverter
    fun toListString(listString: String?): List<String>? = listString?.split(",")
}