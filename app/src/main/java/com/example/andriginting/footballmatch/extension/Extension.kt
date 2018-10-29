package com.example.andriginting.footballmatch.extension

import android.content.Context
import android.view.View
import com.example.andriginting.footballmatch.db.DBHelper
import org.jetbrains.anko.db.MapRowParser

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val Context.databaseHelper: DBHelper
    get() = DBHelper.getDbInstance(context = applicationContext)

fun <T> mapRowParser(parser: (Map<String, Any?>) -> T): MapRowParser<T> =
        object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        }

