package ibu.edu.unitask.data.repository

import ibu.edu.unitask.data.models.Task
import kotlinx.coroutines.flow.Flow

interface UniTaskRepository {

        fun getAllTasks(): Flow<List<Task>>

        fun getTaskById(id: Int): Flow<Task>

        suspend fun insertTask(task: Task)

        suspend fun updateTask(task: Task)

        suspend fun deleteTask(task: Task)
}