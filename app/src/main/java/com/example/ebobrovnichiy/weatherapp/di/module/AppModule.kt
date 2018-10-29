package com.example.ebobrovnichiy.weatherapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.ebobrovnichiy.weatherapp.BuildConfig.BASE_URL
import com.example.ebobrovnichiy.weatherapp.BuildConfig.KEY_API
import com.example.ebobrovnichiy.weatherapp.data.network.api.WeatherService
import com.example.ebobrovnichiy.weatherapp.data.db.dao.CityInfoDao
import com.example.ebobrovnichiy.weatherapp.data.db.dao.ForecastDao
import com.example.ebobrovnichiy.weatherapp.data.db.WeatherDb
import com.example.ebobrovnichiy.weatherapp.data.db.dao.WeatherForecastDao
import com.example.ebobrovnichiy.weatherapp.utilit.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideWeatherService(): WeatherService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val modifiedUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("units", "metric")
                    .addQueryParameter("appid", KEY_API)
                    .build()

            val requestBuilder = original.newBuilder()
                    .url(modifiedUrl)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): WeatherDb {
        return Room
                .databaseBuilder(app, WeatherDb::class.java, "weather.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideCityInfoDao(db: WeatherDb): CityInfoDao {
        return db.cityInfoDao()
    }

    @Singleton
    @Provides
    fun provideForecastDao(db: WeatherDb): ForecastDao {
        return db.forecastDao()
    }

    @Singleton
    @Provides
    fun provideWeatherForecast(db: WeatherDb): WeatherForecastDao {
        return db.weatherForecastDao()
    }
}