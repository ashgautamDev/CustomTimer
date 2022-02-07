package com.ashish.custometimer.model

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.reflect.Type


class DataConverter {

        @TypeConverter
        fun fromList(value : List<Task>) = Json.encodeToString(value)

        @TypeConverter
        fun toList(value: String) = Json.decodeFromString<List<Task>>(value)
    }
//    @TypeConverter
//    fun fromTasksList(tasks: List<Task>?): String? {
//        if (tasks == null) {
//            return null
//        }
//        val gson = Gson()
//        val type: Type = object :
//            TypeToken<List<Task>?>() {
//
//        }.type
//        return gson.toJson(tasks, type)
//    }
//
//    @TypeConverter
//    fun toTasksList(tasks: String?): List<Task>? {
//        if (tasks == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object :
//            TypeToken<List<Task>?>() {}.type
//        return gson.fromJson(tasks, type)
//    }
//}