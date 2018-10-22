package com.example.ebobrovnichiy.weatherapp.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class CityInfo(
        val id: Int,
        val name: String,
        val country: String,
        @SerializedName("coord")
        @Embedded
        val location: Location
)