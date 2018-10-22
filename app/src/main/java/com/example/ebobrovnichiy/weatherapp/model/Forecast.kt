package com.example.ebobrovnichiy.weatherapp.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.example.ebobrovnichiy.weatherapp.db.DataTypeConverter
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(DataTypeConverter::class)
data class Forecast(
        val cityId: Int,
        @SerializedName("dt")
        val date: Int,
        @SerializedName("main")
        @Embedded
        val main: Main,
        @SerializedName("weather")
        val weathers: List<Weather>,
        @Embedded
        val wind: Wind
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}