package com.example.ebobrovnichiy.weatherapp.db

import com.google.gson.annotations.SerializedName

data class Owner(
        @field:SerializedName("work")
        val work: String,
        @field:SerializedName("url")
        val url: String?
)