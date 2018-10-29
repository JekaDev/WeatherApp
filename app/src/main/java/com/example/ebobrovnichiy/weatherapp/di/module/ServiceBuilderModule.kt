package com.example.ebobrovnichiy.weatherapp.di.module

import com.example.ebobrovnichiy.weatherapp.syncservice.ForecastUpdateJobService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeUpdateService(): ForecastUpdateJobService
}