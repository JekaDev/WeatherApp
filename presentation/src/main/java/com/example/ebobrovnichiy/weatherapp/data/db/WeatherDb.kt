package com.example.ebobrovnichiy.weatherapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityWeatherDao
import com.example.ebobrovnichiy.weatherapp.data.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.data.model.Forecast
import com.example.ebobrovnichiy.weatherapp.data.model.CityWeather

@Database(
        entities = [CityInfo::class, Forecast::class, CityWeather::class],
        version = 3,
        exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {

    abstract fun cityInfoDao(): CityInfoDao

    abstract fun forecastDao(): ForecastDao

    abstract fun weatherForecastDao(): CityWeatherDao
}