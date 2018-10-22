package com.example.ebobrovnichiy.weatherapp.ui.city

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.ebobrovnichiy.weatherapp.dto.Resource
import com.example.ebobrovnichiy.weatherapp.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.repository.WeatherRepository
import javax.inject.Inject

class CityViewModel
@Inject
constructor(
        private val repository: WeatherRepository
) : ViewModel() {

    fun addNewCity(lat: Double, lon: Double) {
        repository.citiesInfo(lat, lon)
    }

    fun citiesInfo(): LiveData<Resource<List<CityInfo>>> {
        return repository.citiesInfoDb()
    }

    fun delete(cityInfo: CityInfo){
        repository.deleteCityInfo(cityInfo)
    }
}