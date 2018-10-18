package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.ApiEmptyResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiErrorResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiSuccessResponse
import com.example.ebobrovnichiy.weatherapp.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.model.ForecastResponse
import com.example.ebobrovnichiy.weatherapp.utilit.Resource

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource){data ->
            result.addSource(dbSource){newData ->
                val jhwbh = ""
            }
        }
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                    }
                }
                is ApiEmptyResponse -> {

                }
                is ApiErrorResponse -> {

                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun loadFromDb(): LiveData<List<CityInfo>>
}