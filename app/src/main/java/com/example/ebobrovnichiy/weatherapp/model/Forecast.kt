package com.example.ebobrovnichiy.weatherapp.model

import android.arch.persistence.room.*
import com.example.ebobrovnichiy.weatherapp.db.DataTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = arrayOf(ForeignKey(entity = CityInfo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("cityId"),
        onDelete = ForeignKey.CASCADE)))

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