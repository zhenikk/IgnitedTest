package com.eugens.ignitedtest.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun getFormattedDate(time: Long): String {
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val date = Date(time)
            return dateFormat.format(date)
        }
    }
}