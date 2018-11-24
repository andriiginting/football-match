package com.example.andriginting.footballmatch.view.main.component

import android.view.Gravity
import android.view.ViewGroup
import com.example.andriginting.footballmatch.R
import org.jetbrains.anko.*

class FootbalItem : AnkoComponent<ViewGroup> {
    companion object {
        const val linearItem = 1
        const val imageClubItem = 2
        const val textCLubName = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            id = linearItem
            padding = dip(10)
            gravity = Gravity.CENTER_VERTICAL
            imageView(R.drawable.img_barca) {
                id = imageClubItem
            }.lparams(width = 120, height = 120) {
                margin = dip(10)
            }

            textView(context.getString(R.string.club_name)) {
                id = textCLubName
                textSize = 16f
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }

}