package com.ashish.custometimer.model

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Custome Tasks")
data class CustomeTask(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var pid: Long? = null,
    @ColumnInfo(name="CustomTaskTimer")
    var title: String = "",

    @TypeConverters(DataConverter::class)
    @Ignore
    @NotNull
    @Embedded
    @ColumnInfo(name = "TasksLists")
    var tasks: List<Task>  = emptyList()
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
            tasks = tasks
        )
    }

}
