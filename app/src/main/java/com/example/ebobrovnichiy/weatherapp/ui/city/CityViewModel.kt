package com.example.ebobrovnichiy.weatherapp.ui.city

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.example.ebobrovnichiy.weatherapp.repository.CityRepository
import com.example.ebobrovnichiy.weatherapp.utilit.Resource
import javax.inject.Inject

class CityViewModel
@Inject
constructor(
        val repository: CityRepository
) : ViewModel() {

    fun addData(code: String) : LiveData<Resource<ForecastResponse>>{
        val data = repository.weatherForecast(51.5073509,-0.1277583)
        val oko = ""
        return data;
    }
}