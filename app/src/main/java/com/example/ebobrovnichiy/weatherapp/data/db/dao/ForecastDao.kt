package com.example.ebobrovnichiy.weatherapp.data.db.dao

import android.arch.persistence.room.*
import com.example.ebobrovnichiy.weatherapp.data.model.Forecast

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: List<Forecast>)
}