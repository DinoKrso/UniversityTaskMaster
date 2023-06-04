package ibu.edu.unitask.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.unitask.R


@Composable
fun AllTasksCompleted() {
    val checkImage = painterResource(id = R.drawable.checkimage)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECE7EE)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(painter = checkImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Text(text = stringResource(R.string.all_tasks_completed),
            fontSize = 20.sp,
            fontWeight = FontWeight.W900,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
fun AllTasksCompletedPreview() {
    AllTasksCompleted()
}