package com.example.ebobrovnichiy.weatherapp.data.model

import android.arch.persistence.room.*
import com.example.ebobrovnichiy.weatherapp.data.db.DataTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(
        entity = CityInfo::class,
        parentColumns = ["id"],
        childColumns = ["cityId"],
        onDelete = ForeignKey.CASCADE)])

@TypeConverters(DataTypeConverter::class)
data class Forecast(
        var cityId: Int,
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