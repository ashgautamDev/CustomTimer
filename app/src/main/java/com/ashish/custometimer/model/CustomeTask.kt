package com.ashish.custometimer.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Custome Tasks")
@Serializable
data class CustomeTask(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var pid: Long? = null,
    @ColumnInfo(name="CustomTaskTimer")
    var title: String = "",

    @TypeConverters(DataConverter::class)
    @NotNull
    @ColumnInfo(name = "TaskList")
    var instructTasks: List<Task> = emptyList()
) {
    companion object {
        private val tasks = listOf(
            Task("Skipping", "5"),
            Task("Break", "3"),
            Task("Skipping High", "5"),
            Task("Break", "3"),
            Task("PushUps", "5"),
            Task("Break", "2"),
        )


        fun mock() = CustomeTask(
            pid = 0,
            title = "Workout",
            instructTasks = tasks
        )
    }

}
