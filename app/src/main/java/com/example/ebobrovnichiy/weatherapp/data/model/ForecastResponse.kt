package com.example.ebobrovnichiy.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
        @SerializedName("city")
        val cityInfo: CityInfo,
        @SerializedName("list")
        val forecasts: List<Forecast>
)