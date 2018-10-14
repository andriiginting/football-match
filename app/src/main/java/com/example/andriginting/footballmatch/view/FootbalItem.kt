package com.example.andriginting.footballmatch.view

import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.example.andriginting.footballmatch.R
import org.jetbrains.anko.*

class FootbalItem: AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        linearLayout {
            imageView {

            }.lparams(width = 40, height = 40){
                margin = dip(10)
            }

            textView(context.getString(R.string.club_name)) {
                textSize = 16f
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }

}