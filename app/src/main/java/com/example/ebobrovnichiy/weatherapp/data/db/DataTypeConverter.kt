package com.example.ebobrovnichiy.weatherapp.data.db

import android.arch.persistence.room.TypeConverter
import com.example.ebobrovnichiy.weatherapp.BuildConfig.IMAGE_URL
import com.example.ebobrovnichiy.weatherapp.data.model.Weather
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
    fun listToString(someObjects: List<Weather>): String {

        someObjects.forEach { item ->
            item.iconUrl = IMAGE_URL.replace("*", item.icon)
        }

        return gson.toJson(someObjects)
    }
}