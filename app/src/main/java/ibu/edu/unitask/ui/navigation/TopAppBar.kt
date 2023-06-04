package ibu.edu.unitask.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniTaskTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(title,  color = Color(0xFFFFFFF7)) },
            modifier = modifier ,
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF320064)),
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color(0xFFFFFFF7)
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(title, color = Color(0xFFFFFFF7) ,style= MaterialTheme.typography.bodyLarge) },
            modifier = modifier,
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF320064) ))
    }
}