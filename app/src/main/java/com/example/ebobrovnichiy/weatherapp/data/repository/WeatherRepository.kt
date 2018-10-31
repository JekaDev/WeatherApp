package com.example.ebobrovnichiy.weatherapp.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.data.network.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.data.db.WeatherDb
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityWeatherDao
import com.example.ebobrovnichiy.weatherapp.data.network.dto.*
import com.example.ebobrovnichiy.weatherapp.data.model.Forecast
import com.example.ebobrovnichiy.weatherapp.data.model.CityWeather
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val weatherService: WeatherService,
        private val cityInfoDao: CityInfoDao,
        private val forecastDao: ForecastDao,
        private val cityWeatherDao: CityWeatherDao,
        private val db: WeatherDb
) {

    private val result = MediatorLiveData<Resource<List<CityWeather>>>()

    companion object {
        val TAG = WeatherRepository::class.java.simpleName
    }

    /*fun citiesInfo(lat: Double, lon: Double): LiveData<Resource<List<CityInfo>>> {
        return object : NetworkBoundResource<List<CityInfo>, ForecastResponse>(appExecutors) {

            override fun shouldFetch(data: List<CityInfo>?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<ForecastResponse>> {
                return weatherService.requestForecastForCity(lat, lon)
            }

            override fun saveCallResult(item: ForecastResponse) {
                cityInfoDao.insert(item.cityInfo)
            }

            override fun loadFromDb(): LiveData<List<CityInfo>> {
                val data = cityInfoDao.findAll()
                return data;
            }

        }.asLiveData()
    }*/

    fun update() {
        val kmk = ""
    }

    fun citiesInfo(lat: Double, lon: Double) {
        result.value = Resource.loading(null)
        appExecutors.networkIO().execute {
            val apiResponse = weatherService.requestForecastForCity(lat, lon)
            result.addSource(apiResponse) { response ->
                when (response) {
                    is ApiSuccessResponse -> {
                        appExecutors.diskIO().execute {
                            db.beginTransaction()
                            try {
                                cityInfoDao.insert(response.body.cityInfo)
                                val cityId = response.body.cityInfo.id
                                response.body.forecasts.forEach { item ->
                                    forecastDao.insert(Forecast(
                                            cityId,
                                            item.date,
                                            item.main,
                                            item.weathers,
                                            item.wind))
                                }
                                db.setTransactionSuccessful()
                            } finally {
                                db.endTransaction()
                            }
                        }
                    }
                    is ApiEmptyResponse -> {
                        TODO()
                    }
                    is ApiErrorResponse -> {
                        result.value = Resource.error(response.errorMessage, null)
                    }
                }
            }
        }
    }

    fun citiesInfoDb(): LiveData<Resource<List<CityWeather>>> {

        result.value = Resource.loading(null)
        appExecutors.diskIO().execute {
            val response = cityWeatherDao.citiesWeather()
            result.addSource(response) { newData ->
                result.value = Resource.success(response.value)
            }
        }
        return result
    }

    fun deleteCityInfo(cityId: Int) {
        result.value = Resource.loading(null)
        appExecutors.diskIO().execute {
            cityInfoDao.delete(cityId)
        }
    }
}