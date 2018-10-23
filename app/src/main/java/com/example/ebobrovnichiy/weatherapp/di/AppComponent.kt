package com.example.ebobrovnichiy.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.ebobrovnichiy.weatherapp.WeatherApp
import com.example.ebobrovnichiy.weatherapp.di.module.AppModule
import com.example.ebobrovnichiy.weatherapp.di.module.MainActivityModule
import com.example.ebobrovnichiy.weatherapp.syncservice.ForecastUpdateJobService
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(weatherApp: WeatherApp)

    fun inject(jobSvc: ForecastUpdateJobService)
}

val Context.weatherApp: WeatherApp
    get() = this.applicationContext as WeatherApp