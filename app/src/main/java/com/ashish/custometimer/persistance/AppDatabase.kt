package com.ashish.custometimer.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.custometimer.model.CustomeTask


@Database(entities = [CustomeTask::class] , version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customeTaskDao() : CustomeTaskDao
}