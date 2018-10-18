package com.example.ebobrovnichiy.weatherapp.model

import android.arch.persistence.room.Entity

@Entity
data class Location(
        val lat: Double,
        val lon: Double
)