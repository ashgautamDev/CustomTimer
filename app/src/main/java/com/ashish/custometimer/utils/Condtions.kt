package com.ashish.custometimer.utils

import com.ashish.custometimer.model.Task

fun subTaskEmptyCondition(title : String , time : String) : Boolean
{
    return !(title.isBlank() || time.isBlank() || time.contentEquals(Int.toString()))
}

fun statusText(title: String , tasks : List<Task> , task: Task ) : String {

    return if (title.isNotBlank()){
        if (tasks.isNotEmpty()){
            "Add more instructions"

        } else {
            if (task.taskTime.isNotBlank()) "add first instruction to $title" else "add time to ${task.taskTitle}"
        }
    } else {
        "Add Title to Task"
    }
}
