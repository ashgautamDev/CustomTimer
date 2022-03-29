package com.ashish.custometimer.persistance

import androidx.room.*
import com.ashish.custometimer.model.CustomeTask
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomeTaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomeTask(customeTask: CustomeTask)

    @Query("SELECT * FROM `Custome Tasks` WHERE pid = :id_")
    fun getCustomeTask(id_: Long): Flow<CustomeTask>

    @Query("SELECT * FROM `Custome tasks` ORDER BY pid DESC")
    fun getCustomeTaskList(): Flow<List<CustomeTask>>

    @Delete
    suspend fun deleteTasks(customeTask: CustomeTask)

    @Query("DELETE FROM `Custome tasks`")
    fun deleteAllTask()

}