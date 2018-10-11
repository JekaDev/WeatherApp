package com.example.ebobrovnichiy.weatherapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class City(
        @PrimaryKey(autoGenerate = true)
        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("country")
        val country: String
)