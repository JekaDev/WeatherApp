package com.example.ebobrovnichiy.weatherapp.di.module

import com.example.ebobrovnichiy.weatherapp.syncservice.ForecastUpdateJobService
import com.example.ebobrovnichiy.weatherapp.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeUpdateService() : ForecastUpdateJobService
}