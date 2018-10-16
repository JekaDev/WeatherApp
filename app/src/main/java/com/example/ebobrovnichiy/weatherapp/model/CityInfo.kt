package com.example.ebobrovnichiy.weatherapp.model

import com.google.gson.annotations.SerializedName

data class CityInfo(
        val id: Int,
        val name: String,
        val country: String,
        @SerializedName("coord")
        val location: Location
)