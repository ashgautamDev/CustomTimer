package com.ashish.custometimer.utils

import androidx.compose.runtime.mutableStateListOf
import com.ashish.custometimer.model.Instruction

fun calculateTime(instruction: Instruction): Int {
    var time = 0
    val totalTime = mutableStateListOf<Int>()
    val taskList = instruction.task.listIterator()
    taskList.forEach {
        totalTime.add(it.time)
    }
    totalTime.forEach {
        time += it
    }
    return time
}