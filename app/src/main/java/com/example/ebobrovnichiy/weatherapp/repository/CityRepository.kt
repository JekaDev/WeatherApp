package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.LiveData
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.ApiResponse
import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.example.ebobrovnichiy.weatherapp.utilit.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val weatherService: WeatherService,
        private val cityInfoDao: CityInfoDao
) {


    fun weatherForecast(lat: Double, lon: Double): LiveData<Resource<ForecastResponse>> {
        return object : NetworkBoundResource<ForecastResponse, ForecastResponse>(appExecutors) {

            override fun loadFromDb(): LiveData<List<CityInfo>> {
                val data = cityInfoDao.findByName("London")
                return data
            }

            override fun saveCallResult(item: ForecastResponse) {
                cityInfoDao.insertCityInfo(item.cityInfo)
            }

            override fun createCall(): LiveData<ApiResponse<ForecastResponse>> {

                val data = weatherService.requestForecastForCity(lat, lon)
                return data
            }
        }.asLiveData()
    }
}