package com.example.ebobrovnichiy.weatherapp.repository

import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
        private val weatherService: WeatherService
) {
}