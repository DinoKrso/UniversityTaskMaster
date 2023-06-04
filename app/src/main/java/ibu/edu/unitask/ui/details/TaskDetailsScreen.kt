package ibu.edu.unitask.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ibu.edu.unitask.R
import ibu.edu.unitask.ui.navigation.NavigationDestination
import ibu.edu.unitask.ui.navigation.UniTaskTopAppBar
import ibu.edu.unitask.ui.utils.DateFormatter
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight


object TaskDetailsDestination : NavigationDestination{
    override val route = "task_details/{id}"
    override val titleRes = R.string.task_no
    override val icon = Icons.Default.ArrowForward
}

@SuppressLint("ServiceCast", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskId: Int,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    onNavigateBack: () -> Unit
) {
    val viewModel = viewModel(modelClass = TaskDetailsViewModel::class.java)
    val taskDetailsUiState = viewModel.state
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    LaunchedEffect(key1 = true) {
        viewModel.getTask(taskId)
    }

    Scaffold(
        topBar = {
            UniTaskTopAppBar(
                title = stringResource(TaskDetailsDestination.titleRes) + taskDetailsUiState.task.id.toString(),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = modifier
                .background(Color(0xFFECE7EE))
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Task Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Checkbox(
                    checked = taskDetailsUiState.task.isFinished,
                    onCheckedChange = {}
                )
                Text(
                    text = "Status: ",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = if (taskDetailsUiState.task.isFinished) "Done" else "Not done",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(
                iconResId = R.drawable.baseline_info_24,
                title = "Title: ",
                content = taskDetailsUiState.task.title,
                clipboardManager = clipboardManager
            )

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(
                iconResId = R.drawable.baseline_description_24,
                title = "Description: ",
                content = taskDetailsUiState.task.description,
                clipboardManager = clipboardManager
            )

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(
                iconResId = R.drawable.baseline_subject_24,
                title = "Course: ",
                content = taskDetailsUiState.task.course,
                clipboardManager = clipboardManager
            )

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(
                iconResId = R.drawable.baseline_date_range_24,
                title = "Due date: ",
                content = DateFormatter(taskDetailsUiState.task.dueDate),
                clipboardManager = clipboardManager
            )
        }
    }
}
@Composable
private fun InfoRow(
    iconResId: Int,
    title: String,
    content: String,
    clipboardManager: ClipboardManager
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF320064)
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_content_copy_24),
            contentDescription = "copy",
            modifier = Modifier
                .clickable {
                    val annotatedString = buildAnnotatedString {
                        append(content)
                    }

                    clipboardManager.setText(annotatedString)
                    Toast
                        .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                        .show()
                }
                .padding(end = 10.dp)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            tint = Color(0xFFFFFFF7)
        )
    }
}
