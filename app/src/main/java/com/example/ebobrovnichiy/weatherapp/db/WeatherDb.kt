package com.example.ebobrovnichiy.weatherapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ebobrovnichiy.weatherapp.model.CityInfo

@Database(
        entities = [
            User::class],
        version = 3,
        exportSchema = false
)
abstract class WeatherDb: RoomDatabase(){

    abstract fun userDao(): UserDao
}