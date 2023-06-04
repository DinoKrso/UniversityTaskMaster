package ibu.edu.unitask.data.repository

import ibu.edu.unitask.data.dao.TaskDao
import ibu.edu.unitask.data.models.Task
import kotlinx.coroutines.flow.Flow

class OfflineRepository(
    private val TaskDao: TaskDao
) : UniTaskRepository {
    override fun getAllTasks(): Flow<List<Task>> = TaskDao.getAllTasks()

    override fun getTaskById(id: Int): Flow<Task> = TaskDao.getTaskById(id)

    override suspend fun insertTask(task: Task) = TaskDao.insertTask(task)

    override suspend fun updateTask(task: Task) = TaskDao.updateTask(task)

    override suspend fun deleteTask(task: Task) = TaskDao.deleteTask(task)
}