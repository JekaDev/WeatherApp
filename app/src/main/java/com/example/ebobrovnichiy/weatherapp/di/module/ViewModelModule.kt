package com.example.ebobrovnichiy.weatherapp.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ebobrovnichiy.weatherapp.di.ViewModelKey
import com.example.ebobrovnichiy.weatherapp.ui.city.CityViewModel
import com.example.ebobrovnichiy.weatherapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel::class)
    abstract fun bindCityViewModel(cityViewModel: CityViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}