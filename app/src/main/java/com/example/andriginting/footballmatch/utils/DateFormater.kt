package com.example.andriginting.footballmatch.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateFormater {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun dateFormater(dates: String?): String?{
            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date = inputFormat.parse(dates) ?: " "
            return outputFormat.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun timeFormatter(time: String?): String?{
            val input = SimpleDateFormat("HH:mm", Locale.getDefault())
            input.timeZone = TimeZone.getTimeZone("GMT")
            val  out = input.parse(time) ?: " "
            return input.format(out)
        }
    }
}