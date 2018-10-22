package com.example.ebobrovnichiy.weatherapp.db

import android.arch.persistence.room.TypeConverter
import com.example.ebobrovnichiy.weatherapp.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class DataTypeConverter {

    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Weather> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Weather>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun ListToString(someObjects: List<Weather>): String {
        return gson.toJson(someObjects)
    }
}