package ibu.edu.unitask.ui.finished

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ibu.edu.unitask.data.graph.Graph
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.data.repository.OfflineRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class FinishedTasksViewModel (
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(FinishedTasksUiState())
        private set

    init{
        getTasks()
        getCalcTasks()
        getWebTasks()
        getMobileTasks()
        getDataStrucTasks()
        getOtherTasks()
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
        closeDeleteDialog()
        denyDeletion()
    }

    fun getOtherTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { tasks ->
                val othTasks = mutableListOf<Task>()
                tasks.forEach { task ->
                    if (
                        task.course != "C2" &&
                        task.course != "MOBILE" &&
                        task.course != "WEB" &&
                        task.course != "DS" &&
                        task.isFinished
                    ) {
                        othTasks.add(task)
                        state = state.copy(otherTasks = othTasks)
                    }
                }
            }
        }
    }

    fun getCalcTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { tasks ->
                val calcTasks = mutableListOf<Task>()
                tasks.forEach { task ->
                    if (task.course == "C2" && task.isFinished) {
                        calcTasks.add(task)
                        state = state.copy(calculusTasks = calcTasks)
                    }
                }
            }
        }
    }

    fun getWebTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { tasks ->
                val webTasks = mutableListOf<Task>()
                tasks.forEach { task ->
                    if (task.course == "WEB" && task.isFinished) {
                        webTasks.add(task)
                        state = state.copy(webProgTasks = webTasks)
                    }
                }
            }
        }
    }

    fun getDataStrucTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { tasks ->
                val dataTasks = mutableListOf<Task>()
                tasks.forEach { task ->
                    if (task.course == "DS" && task.isFinished) {
                        dataTasks.add(task)
                        state = state.copy(dataStrucTasks = dataTasks)
                    }
                }
            }
        }
    }

    fun getMobileTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { tasks ->
                val mobileTasks = mutableListOf<Task>()
                tasks.forEach { task ->
                    if (task.course == "MOBILE" && task.isFinished) {
                        mobileTasks.add(task)
                        state = state.copy(mobileProgTasks = mobileTasks)
                    }
                }
            }
        }
    }

    private fun getTasks(){
        viewModelScope.launch {
            repository.getAllTasks().collectLatest {
                state = state.copy(tasks = it)
            }
        }
    }

    fun assignTaskForDeletion(task: Task){
        state = state.copy(taskForDeletion = task)
    }

    fun openDeleteDialog(){
        state = state.copy(openDeleteDialog = true)
    }

    fun closeDeleteDialog(){
        state = state.copy(openDeleteDialog = false)
    }

    fun confirmDeletion() {
        viewModelScope.launch {
            val taskForDeletion = state.taskForDeletion
            repository.deleteTask(taskForDeletion)

            val updatedCalculusTasks = state.calculusTasks.toMutableList().apply {
                remove(taskForDeletion)
            }
            val updatedDataTasks = state.dataStrucTasks.toMutableList().apply {
                remove(taskForDeletion)
            }
            val updatedWebTasks = state.webProgTasks.toMutableList().apply {
                remove(taskForDeletion)
            }
            val updatedMobileTasks = state.mobileProgTasks.toMutableList().apply {
                remove(taskForDeletion)
            }
            val updatedOtherTasks = state.otherTasks.toMutableList().apply {
                remove(taskForDeletion)
            }
            state = state.copy(
                calculusTasks = updatedCalculusTasks,
                dataStrucTasks = updatedDataTasks,
                webProgTasks = updatedWebTasks,
                mobileProgTasks = updatedMobileTasks,
                otherTasks = updatedOtherTasks,
                confirmDelete = false
            )

            state = state.copy(
                confirmDelete = true
            )

            closeDeleteDialog()
        }
    }


    fun denyDeletion(){
        state = state.copy(confirmDelete = false)
    }
}

data class FinishedTasksUiState(
    val tasks: List<Task> = emptyList(),
    val calculusTasks: List<Task> = emptyList(),
    val webProgTasks: List<Task> = emptyList(),
    val mobileProgTasks: List<Task> = emptyList(),
    val dataStrucTasks: List<Task> = emptyList(),
    val otherTasks: List<Task> = emptyList(),
    val openDeleteDialog: Boolean = false,
    val confirmDelete: Boolean = false,
    val taskForDeletion: Task = Task(
        id = -1,
        title = "",
        course = "",
        description = "",
        dueDate = Date()
    ),
)