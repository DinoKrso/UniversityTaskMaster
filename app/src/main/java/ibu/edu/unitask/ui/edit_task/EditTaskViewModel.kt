package ibu.edu.unitask.ui.edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ibu.edu.unitask.data.graph.Graph
import ibu.edu.unitask.data.repository.OfflineRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class EditTaskViewModel (
    private val repository: OfflineRepository = Graph.repository
) : ViewModel(){

    var state by mutableStateOf(EditTaskUiState())
        private set

    fun getTask(id: Int){
        viewModelScope.launch {
            repository.getTaskById(id).collectLatest {
                onTitleChange(it.title)
                onDescriptionChange(it.description)
                onCourseChange(it.course)
                onDateChange(it.dueDate)
                state = state.copy(isFinished = it.isFinished)
            }
        }
    }


    fun onTitleChange(newTitle: String){
        state = state.copy(taskTitle = newTitle)
    }
    fun onDescriptionChange(newDescription: String){
        state = state.copy(taskDescription = newDescription)
    }
    fun onCourseChange(newCourse: String){
        state = state.copy(taskCourse = newCourse)
    }
    fun onDateChange(newDate: Date){
        state = state.copy(dueDate = newDate)
    }
}

data class EditTaskUiState(
    val taskId: Int = -1,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskCourse: String = "",
    val dueDate: Date = Date(),
    val isFinished: Boolean = false
)