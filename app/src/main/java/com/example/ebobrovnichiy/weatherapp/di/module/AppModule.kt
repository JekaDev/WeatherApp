package com.example.ebobrovnichiy.weatherapp.di.module

import com.example.ebobrovnichiy.weatherapp.BuildConfig.BASE_URL
import com.example.ebobrovnichiy.weatherapp.BuildConfig.KEY_API
import com.example.ebobrovnichiy.weatherapp.api.WeatherService
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
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
                //.addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
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
}