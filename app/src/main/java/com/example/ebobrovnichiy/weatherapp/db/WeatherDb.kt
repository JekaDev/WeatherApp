package com.example.ebobrovnichiy.weatherapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ebobrovnichiy.weatherapp.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.model.CityInfo

@Database(entities = [CityInfo::class], version = 1, exportSchema = false)
abstract class WeatherDb: RoomDatabase(){

    abstract fun cityInfoDao(): CityInfoDao
}