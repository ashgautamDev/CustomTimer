package com.ashish.custometimer.model

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataConverter {


    @TypeConverter // note this annotation
    fun fromOptionValuesList(tasks: List<CustomeTask>?): String? {
        if (tasks == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<CustomeTask>?>() {}.type
        return gson.toJson(tasks, type)
    }


    @TypeConverter // note this annotation
    fun toOptionValuesList(tasks: String?): List<CustomeTask>? {
        if (tasks == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<CustomeTask>?>() {}.type
        return gson.fromJson(tasks, type)
    }
}