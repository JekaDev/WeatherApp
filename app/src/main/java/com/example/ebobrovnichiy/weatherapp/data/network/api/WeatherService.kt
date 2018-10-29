package com.example.ebobrovnichiy.weatherapp.data.network.api

import android.arch.lifecycle.LiveData
import com.example.ebobrovnichiy.weatherapp.data.network.dto.ApiResponse
import com.example.ebobrovnichiy.weatherapp.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    fun requestForecastForCity(
            @Query("lat") lat : Double,
            @Query("lon") lon : Double
    ): LiveData<ApiResponse<ForecastResponse>>
}