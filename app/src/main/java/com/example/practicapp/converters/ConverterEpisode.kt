package com.example.practicapp.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterEpisode {

        @TypeConverter
        fun fromString(value: String?): List<String>? {
            var listType  =
                object : TypeToken<List<String?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromList(list: List<String?>?): String? {
            var gson: Gson = Gson()
            var json = gson.toJson(list).toString()
            return json
        }

}