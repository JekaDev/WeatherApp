package com.example.ebobrovnichiy.weatherapp.data.model

import android.arch.persistence.room.Entity

@Entity
data class Location(
        val lat: Double,
        val lon: Double
)