package com.example.ebobrovnichiy.weatherapp.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.ebobrovnichiy.weatherapp.data.model.CityWeather

@Dao
interface CityWeatherDao {

    @Query("""
        SELECT * FROM Forecast
        INNER JOIN CityInfo ON CityInfo.id = Forecast.cityId
        WHERE date(datetime(Forecast.date , 'unixepoch', 'localtime')) = date('now', 'localtime')
        AND Forecast.date = (SELECT MIN(Forecast.date) FROM Forecast)"""
    )
    fun citiesWeather(): LiveData<List<CityWeather>>
}