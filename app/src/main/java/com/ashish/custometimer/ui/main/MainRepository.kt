package com.ashish.custometimer.ui.main


import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.persistance.CustomeTaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val customeTaskDao: CustomeTaskDao
) {

    fun getAllCustomeTasks(): Flow<List<CustomeTask>> =
        customeTaskDao.getCustomeTaskList()

  fun getCustomeTask(id: Long): Flow<CustomeTask> =
        customeTaskDao.getCustomeTask(id)

    suspend fun deleteCustomeTask(customeTask: CustomeTask) =
        customeTaskDao.deleteTasks(customeTask)

    suspend fun insertCustomeTask(customeTask: CustomeTask) =
        customeTaskDao.insertCustomeTask(customeTask)

    fun deleteAllCustomTask(){
        customeTaskDao.deleteAllTask()
    }

}