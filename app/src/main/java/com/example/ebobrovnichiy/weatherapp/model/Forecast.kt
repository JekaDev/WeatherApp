package com.example.ebobrovnichiy.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Forecast(
        @SerializedName("dt")
        val date: Int,
        @SerializedName("main")
        val main: Main,
        @SerializedName("weather")
        val weathers: List<Weather>,
        val wind: Wind
)