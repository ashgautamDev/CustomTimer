package com.ashish.custometimer.utils

import androidx.compose.runtime.mutableStateListOf
import com.ashish.custometimer.model.CustomeTask

fun main(){
    println(calculateTime(CustomeTask.mock()))
}
fun calculateTime(customeTask: CustomeTask): Int {
    var time = 0
    val totalTime = mutableStateListOf<Int>()
    val taskList = customeTask.instructTasks.listIterator()
    taskList.forEach {
        it.taskTime.let { it1 -> totalTime.add(it1.toInt()) }
    }
    totalTime.forEach {
        time += it
    }
    return time
}

fun calculateSec(minute : Int , Second : Int ) : String {
    val sec =  minute*60 + Second
    return sec.toString()
}

