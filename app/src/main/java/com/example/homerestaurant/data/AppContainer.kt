package com.example.homerestaurant.data

import android.content.Context

interface AppContainer {
    val foodRepository: FoodRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val foodRepository: FoodRepository by lazy {
        OfflineFoodRepository(FoodDatabase.getDatabase(context).foodDao())
    }

}