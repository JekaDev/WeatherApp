package com.example.ebobrovnichiy.weatherapp.ui.cities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.ebobrovnichiy.weatherapp.data.network.dto.Resource
import com.example.ebobrovnichiy.weatherapp.data.model.CityWeather
import com.example.ebobrovnichiy.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class CitiesWeatherViewModel
@Inject
constructor(
        private val repository: WeatherRepository
) : ViewModel() {

    fun addNewCity(lat: Double, lon: Double) {
        repository.citiesInfo(lat, lon)
    }

    fun citiesInfo(): LiveData<Resource<List<CityWeather>>> {
        return repository.citiesInfoDb()
    }

    fun delete(cityId: Int){
        repository.deleteCityInfo(cityId)
    }

    fun update(){
        repository.update()
    }
}