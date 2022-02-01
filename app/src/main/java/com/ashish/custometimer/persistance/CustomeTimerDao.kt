package com.ashish.custometimer.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashish.custometimer.model.CustomeTask
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomeTaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomeTask(customeTask: CustomeTask)

    @Query("SELECT * FROM `Custome Tasks` WHERE id = :id_")
    fun getCustomeTask(id_: Long): Flow<CustomeTask>

    @Query("SELECT * FROM `Custome tasks` ORDER BY id DESC")
    fun getCustomeTaskList(): Flow<List<CustomeTask>>


}