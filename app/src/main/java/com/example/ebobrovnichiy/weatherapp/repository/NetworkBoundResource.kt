package com.example.ebobrovnichiy.weatherapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.ebobrovnichiy.weatherapp.AppExecutors
import com.example.ebobrovnichiy.weatherapp.api.ApiEmptyResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiErrorResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiResponse
import com.example.ebobrovnichiy.weatherapp.api.ApiSuccessResponse
import com.example.ebobrovnichiy.weatherapp.db.Owner
import com.example.ebobrovnichiy.weatherapp.db.User
import com.example.ebobrovnichiy.weatherapp.utilit.Resource

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource){newData ->
            var l = ""
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
                    appExecutors.diskIO().execute{

                        saveCallResult(User(1,"weather", null, null, null, null,null, Owner("First", null)))

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
    protected abstract fun saveCallResult(item: User)

    @MainThread
    protected abstract fun loadFromDb(): LiveData<User>



    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body
}