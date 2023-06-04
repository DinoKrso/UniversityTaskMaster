package ibu.edu.unitask.ui.details

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

class TaskDetailsViewModel (
    private val repository: OfflineRepository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(TaskDetailsUiState())
        private set

    fun getTask(id: Int) {
        viewModelScope.launch {
            repository.getTaskById(id).collectLatest {
                state = state.copy(it)
            }
        }
    }
}

data class TaskDetailsUiState(
    val task: Task = Task(
        id = 12,
        title = "",
        description = "",
        course= "",
        dueDate = Date(),
        isFinished = false,
    )
)
