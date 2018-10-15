package com.example.andriginting.footballmatch.extension

import android.os.Handler
import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun <T> (() -> T).withDelay(delay: Long = 500L) {
    Handler().postDelayed({ this.invoke() }, delay)
}

