package com.example.andriginting.footballmatch.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateFormater {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun dateFormater(dates: String?): String?{
            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy")
            val date = inputFormat.parse(dates)
            return outputFormat.format(date)
        }
    }
}