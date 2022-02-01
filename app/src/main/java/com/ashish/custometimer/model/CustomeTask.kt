package com.ashish.custometimer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Custome Tasks")
data class CustomeTask(
    @PrimaryKey val id: Long,
    val title: String,
    val instructions: Instruction
) {
    companion object {
        private val tasks = listOf(
            Task("Skipping", 30),
            Task("Break", 10),
            Task("Skipping High", 30),
            Task("Break", 10),
            Task("PushUps", 30),
            Task("Break", 20),
        )
        private val instr = Instruction(tasks)

        fun mock() = CustomeTask(
            id = 0,
            title = "Workout",
            instructions = instr
        )
    }
}
