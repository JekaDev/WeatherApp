package com.example.ebobrovnichiy.weatherapp.data.model

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class WeatherForecast(
        val id: Int,
        val cityId: Int,
        val name: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val iconUrl: String?,
        val temp: Double,
        val tempMax: Double,
        val tempMin: Double,
        val humidity: Int,
        val speed: Double
)