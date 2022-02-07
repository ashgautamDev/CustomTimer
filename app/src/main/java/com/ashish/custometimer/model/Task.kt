package com.ashish.custometimer.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskTitle : String ,
    val taskTime : String
)
