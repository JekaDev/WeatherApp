package com.example.ebobrovnichiy.weatherapp.dao

import android.arch.persistence.room.*
import com.example.ebobrovnichiy.weatherapp.model.Forecast

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast)
}