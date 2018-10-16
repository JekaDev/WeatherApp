package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.MutableLiveData
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.github.leonardoxh.livedatacalladapter.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val weatherService: WeatherService
) {

    val result = MutableLiveData<Resource<ForecastResponse>>()

    fun weatherForecast() {
        appExecutors.networkIO().execute {
            val forrecast = weatherService.requestForecastForCity(51.5073509,-0.1277583)
            val daya = "ks"
        }
    }
}