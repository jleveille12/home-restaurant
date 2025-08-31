package com.example.homerestaurant

import android.app.Application
import com.example.homerestaurant.data.AppContainer
import com.example.homerestaurant.data.AppDataContainer

class HomeRestaurantApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}