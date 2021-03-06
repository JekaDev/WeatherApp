package com.example.ebobrovnichiy.weatherapp.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.ebobrovnichiy.weatherapp.data.model.CityInfo

@Dao
interface CityInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityInfo: CityInfo)

    @Query("SELECT * FROM cityInfo WHERE id = :id")
    fun findById(id: Int): LiveData<CityInfo>

    @Query("SELECT * FROM cityInfo")
    fun findAll(): LiveData<List<CityInfo>>

    @Query("SELECT * FROM cityInfo")
    fun findCities(): List<CityInfo>

    @Query("DELETE From cityinfo WHERE id = :cityId")
    fun delete(cityId: Int)
}