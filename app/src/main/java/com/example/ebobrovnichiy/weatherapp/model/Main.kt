package com.example.ebobrovnichiy.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Main(
        val temp: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        @SerializedName("temp_min")
        val tempMin: Double,
        val humidity: Int
)
