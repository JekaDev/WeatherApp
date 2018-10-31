package com.example.ebobrovnichiy.weatherapp.di.module


import com.example.ebobrovnichiy.weatherapp.ui.cities.CitiesWeatherListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCitiesListFragment(): CitiesWeatherListFragment

}