package com.example.ebobrovnichiy.weatherapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.WeatherForecastDao
import com.example.ebobrovnichiy.weatherapp.data.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.data.model.Forecast
import com.example.ebobrovnichiy.weatherapp.data.model.WeatherForecast

@Database(
        entities = [CityInfo::class, Forecast::class, WeatherForecast::class],
        version = 3,
        exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {

    abstract fun cityInfoDao(): CityInfoDao

    abstract fun forecastDao(): ForecastDao

    abstract fun weatherForecastDao(): WeatherForecastDao
}