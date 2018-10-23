package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.util.Log
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.db.WeatherDb
import com.example.ebobrovnichiy.weatherapp.dto.*
import com.example.ebobrovnichiy.weatherapp.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.model.Forecast
import com.example.ebobrovnichiy.weatherapp.ui.city.CitiesListFragment
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val weatherService: WeatherService,
        private val cityInfoDao: CityInfoDao,
        private val forecastDao: ForecastDao,
        private val db: WeatherDb
) {

    private val result = MediatorLiveData<Resource<List<CityInfo>>>()

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

    fun citiesInfoDb(): LiveData<Resource<List<CityInfo>>> {
        result.value = Resource.loading(null)
        appExecutors.diskIO().execute {
            val response = cityInfoDao.findAll()
            result.addSource(response) { newData ->
                result.value = Resource.success(response.value)
            }
        }
        return result
    }

    fun deleteCityInfo(cityInfo: CityInfo) {
        result.value = Resource.loading(null)
        appExecutors.diskIO().execute {
            cityInfoDao.delete(cityInfo)
        }
    }
}