package com.example.ebobrovnichiy.weatherapp.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ebobrovnichiy.weatherapp.di.ViewModelKey
import com.example.ebobrovnichiy.weatherapp.ui.cities.CitiesWeatherViewModel
import com.example.ebobrovnichiy.weatherapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CitiesWeatherViewModel::class)
    abstract fun bindCityViewModel(citiesWeatherViewModel: CitiesWeatherViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}