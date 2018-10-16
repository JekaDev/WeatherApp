package com.example.ebobrovnichiy.weatherapp.api

import android.arch.lifecycle.LiveData
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    fun requestForecastForCity(
            @Query("lat") lat : Double,
            @Query("lon") lon : Double
    ): LiveData<Resource<ForecastResponse>>
}