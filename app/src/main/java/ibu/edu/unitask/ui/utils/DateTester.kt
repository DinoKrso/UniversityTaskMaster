package ibu.edu.unitask.ui.utils

import androidx.compose.ui.graphics.Color
import java.util.Date

fun DateTester(date1: Date, date2: Date): Color{
    val date1text = DateFormatter(date1)
    val date2text = DateFormatter(date2)
    return if(date1 > date2 || date1text.equals(date2text)) Color.White else Color(0xFFD11A2A)
}