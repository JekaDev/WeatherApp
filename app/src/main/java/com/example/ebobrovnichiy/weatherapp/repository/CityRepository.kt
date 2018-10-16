package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.MutableLiveData
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val weatherService: WeatherService
) {

    public val result = MutableLiveData<ForecastResponse>()

    fun weatherForecast(){
        appExecutors.networkIO().execute {



        }
    }
}