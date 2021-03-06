package com.ashish.custometimer.utils

import com.ashish.custometimer.model.CustomeTask


sealed class ViewState {

    // It Represents different states

    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val task: List<CustomeTask>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}

sealed class TaskState {

    // It Represents different states

    object Empty : TaskState()
    object Loading : TaskState()
    data class Success(val task: CustomeTask) : TaskState()
    data class Error(val exception: Throwable) : TaskState()
}