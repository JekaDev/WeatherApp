package com.example.ebobrovnichiy.weatherapp.syncservice

import android.util.Log
import com.example.ebobrovnichiy.weatherapp.di.AppInjector
import com.example.ebobrovnichiy.weatherapp.di.weatherApp
import com.example.ebobrovnichiy.weatherapp.data.repository.WeatherRepository
import com.example.ebobrovnichiy.weatherapp.ui.city.CitiesListFragment
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import dagger.android.AndroidInjection
import javax.inject.Inject

class ForecastUpdateJobService : JobService() {

    companion object {
        val TAG = CitiesListFragment::class.java.simpleName!!
    }

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()

        AppInjector.init(weatherApp)
    }

    override fun onStopJob(job: JobParameters?): Boolean {
        Log.d(TAG, "JobService Finished")
        return false
    }

    override fun onStartJob(job: JobParameters?): Boolean {
        Log.d(TAG, "JobService Started")
        weatherRepository.citiesInfo(51.5085, -0.1258)
        return false
    }
}