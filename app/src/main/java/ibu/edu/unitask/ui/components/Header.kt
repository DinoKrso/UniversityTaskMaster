package ibu.edu.unitask.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ibu.edu.unitask.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Header(day: String) {
    //var date : Calendar = Calendar.getInstance(Calendar.DAY_OF_MONTH)

    /* val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)*/

// Get the current date and time
    //  val calendar = Calendar.getInstance()
    // val currentDate = calendar.time

    val dateFormat = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault())
    val currentDate = Date()
    val formattedDate = dateFormat.format(currentDate)

// Format the date and store it in a string variable
    // val formattedDate = dateFormatter.format(currentDate)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFECE7EE))
            .padding(start = 20.dp, top = 20.dp, bottom = 15.dp)
    ){

        Text(text = day,
            //modifier = Modifier.padding(5.dp,5.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            style= MaterialTheme.typography.bodyLarge

        )
        Text(
            text = formattedDate,
            Modifier.padding(5.dp,0.dp),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
    }



}


@Preview
@Composable
fun HeaderPreview() {
    Header(day = "Monday")
}