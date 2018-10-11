package com.example.ebobrovnichiy.weatherapp

import android.app.Application
import com.example.ebobrovnichiy.weatherapp.di.AppInjector
import dagger.android.HasActivityInjector
import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



class WeatherApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}