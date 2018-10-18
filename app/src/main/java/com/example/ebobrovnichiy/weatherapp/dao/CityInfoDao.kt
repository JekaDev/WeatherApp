package com.example.ebobrovnichiy.weatherapp.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ebobrovnichiy.weatherapp.model.CityInfo

@Dao
interface CityInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityInfo(vararg cityInfo: CityInfo)

    @Query("SELECT * FROM cityInfo WHERE name = :name")
    fun findByName(name: String): LiveData<List<CityInfo>>
}