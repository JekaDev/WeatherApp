package com.example.ebobrovnichiy.weatherapp.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.example.ebobrovnichiy.weatherapp.data.db.DataTypeConverter

@Entity(primaryKeys = ["id"])
@TypeConverters(DataTypeConverter::class)
data class CityWeather(
        val id: Int,
        val cityId: Int,
        val name: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val temp: Double,
        val tempMax: Double,
        val tempMin: Double,
        val humidity: Int,
        val speed: Double,
        val weathers: List<Weather>?
)