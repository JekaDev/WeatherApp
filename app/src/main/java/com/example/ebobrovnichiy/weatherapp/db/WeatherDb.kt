package com.example.ebobrovnichiy.weatherapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ebobrovnichiy.weatherapp.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.model.Forecast

@Database(
        entities = [CityInfo::class, Forecast::class],
        version = 3,
        exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {

    abstract fun cityInfoDao(): CityInfoDao

    abstract fun forecastDao(): ForecastDao
}