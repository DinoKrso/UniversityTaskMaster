package ibu.edu.unitask.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val course: String,
    val dueDate: Date,
    val isFinished: Boolean = false,
)
