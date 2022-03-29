package com.ashish.custometimer.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.utils.TaskState
import com.ashish.custometimer.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _taskState = MutableStateFlow<TaskState>(TaskState.Loading)
    val taskState = _taskState.asStateFlow()

    fun insertCustomeTask(customeTask: CustomeTask) =
        viewModelScope.launch {
            repository.insertCustomeTask(customeTask)
        }

    fun deleteCustomeTask(customeTask: CustomeTask) =
        viewModelScope.launch {
            repository.deleteCustomeTask(customeTask)
        }
    fun deleteAllCustomeTask(){
        viewModelScope.launch {
            repository.deleteAllCustomTask()
        }
    }

    fun getCustomeTask(id: Long) = viewModelScope.launch {
        repository.getCustomeTask(id).collect { task ->
            _taskState.value = TaskState.Success(task)
        }
    }


    init {
        viewModelScope.launch {
            repository.getAllCustomeTasks().distinctUntilChanged().collect { tasks ->
                if (tasks.isNullOrEmpty()) {

                    _uiState.value = ViewState.Empty
                } else {
                    _uiState.value = ViewState.Success(tasks)
                }
            }
        }


    }
}