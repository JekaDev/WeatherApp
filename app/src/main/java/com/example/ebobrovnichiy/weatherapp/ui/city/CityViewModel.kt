package com.example.ebobrovnichiy.weatherapp.ui.city

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.example.ebobrovnichiy.weatherapp.repository.CityRepository
import javax.inject.Inject

class CityViewModel
@Inject
constructor(
        val repository: CityRepository
) : ViewModel() {

    val forecastResponse = MutableLiveData<ForecastResponse>()


    fun addData(code: String){


        repository.weatherForecast()
    }

}