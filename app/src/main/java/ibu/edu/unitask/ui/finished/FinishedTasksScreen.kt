package ibu.edu.unitask.ui.finished

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.data.models.Task
import ibu.edu.unitask.ui.components.TaskCardFinished
import ibu.edu.unitask.ui.delete_task_alert.DeleteTaskAlertDialog
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar


object FinishedTasksDestination : NavigationDestination{
    override val route = "finished"
    override val titleRes = R.string.finished_tasks
    override val icon = Icons.Default.CheckCircle

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishedTasksScreen(
    modifier: Modifier = Modifier,
    onRequestDetails: (Int) -> Unit
) {
    val viewModel = viewModel(modelClass = FinishedTasksViewModel::class.java)
    val finishedTasksUiState = viewModel.state
    val context = LocalContext.current

    if (finishedTasksUiState.confirmDelete) {
            Toast.makeText(context, "Task deleted successfully!", Toast.LENGTH_SHORT).show()
            viewModel.denyDeletion()
    }

    Scaffold(
        topBar = {
            UniTaskTopAppBar(
                title = stringResource(id = FinishedTasksDestination.titleRes),
                canNavigateBack = false,
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(bottom = 67.dp)
        ) {

            // Category: Calculus
            item {
                SectionHeader(text = "Calculus:")
            }
            item {
                if (finishedTasksUiState.calculusTasks.isEmpty()) {
                    EmptyTasksText()
                } else {
                    TaskList(
                        tasks = finishedTasksUiState.calculusTasks,
                        viewModel = viewModel,
                        modifier = modifier,
                        onRequestDetails = onRequestDetails
                    )
                }
            }

            // Category: Web Programming
            item {
                SectionHeader(text = "Web programming:")
            }
            item {
                if (finishedTasksUiState.webProgTasks.isEmpty()) {
                    EmptyTasksText()
                } else {
                    TaskList(
                        tasks = finishedTasksUiState.webProgTasks,
                        viewModel = viewModel,
                        modifier = modifier,
                        onRequestDetails = onRequestDetails
                    )
                }
            }

            // Category: Data Structures
            item {
                SectionHeader(text = "Data structures:")
            }
            item {
                if (finishedTasksUiState.dataStrucTasks.isEmpty()) {
                    EmptyTasksText()
                } else {
                    TaskList(
                        tasks = finishedTasksUiState.dataStrucTasks,
                        viewModel = viewModel,
                        modifier = modifier,
                        onRequestDetails = onRequestDetails
                    )
                }
            }

            // Category: Mobile Programming
            item {
                SectionHeader(text = "Mobile programming:")
            }
            item {
                if (finishedTasksUiState.mobileProgTasks.isEmpty()) {
                    EmptyTasksText()
                } else {
                    TaskList(
                        tasks = finishedTasksUiState.mobileProgTasks,
                        viewModel = viewModel,
                        modifier = modifier,
                        onRequestDetails = onRequestDetails
                    )
                }
            }

            // Category: Other
            item {
                SectionHeader(text = "Other:")
            }
            item {
                if (finishedTasksUiState.otherTasks.isEmpty()) {
                    EmptyTasksText()
                } else {
                    TaskList(
                        tasks = finishedTasksUiState.otherTasks,
                        viewModel = viewModel,
                        modifier = modifier,
                        onRequestDetails = onRequestDetails
                    )
                }
            }
        }

        if (finishedTasksUiState.openDeleteDialog) {
            DeleteTaskAlertDialog(
                onDelete = {
                    viewModel.confirmDeletion()
                    viewModel.closeDeleteDialog()
                },
                onDismissRequest = {
                    viewModel.closeDeleteDialog()
                }
            )
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun EmptyTasksText() {
    Text(
        text = "No tasks :(",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray
    )
}

@Composable
private fun TaskList(
    tasks: List<Task>,
    viewModel: FinishedTasksViewModel,
    modifier: Modifier,
    onRequestDetails: (Int) -> Unit
) {
    Column(modifier = modifier) {
        tasks.forEach { task ->
            TaskCardFinished(
                task = task,
                onCheckedChange = { _, _ -> },
                isChecked = task.isFinished,
                onDelete = {
                    viewModel.assignTaskForDeletion(task)
                    viewModel.openDeleteDialog()
                },
                modifier = modifier,
                onRequestDetails = onRequestDetails
            )
        }
    }
}

