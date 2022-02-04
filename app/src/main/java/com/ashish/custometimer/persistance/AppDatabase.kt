package com.ashish.custometimer.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.model.DataConverter


@Database(entities = [CustomeTask::class] , version = 2 , exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customeTaskDao() : CustomeTaskDao
}