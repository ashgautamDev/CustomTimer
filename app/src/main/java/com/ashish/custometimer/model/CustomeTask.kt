package com.ashish.custometimer.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "Custome Tasks")
data class CustomeTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long =  0,
    @NonNull
    val title: String,
    @NonNull
    @TypeConverters(DataConverter::class)
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
