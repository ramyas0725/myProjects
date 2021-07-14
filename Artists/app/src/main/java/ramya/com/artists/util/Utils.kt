package ramya.com.artists.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    fun parseDate(dateString: String){
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = inputFormat.parse(dateString)
        val formattedDate: String = outputFormat.format(date)
        println(formattedDate)
    }

}