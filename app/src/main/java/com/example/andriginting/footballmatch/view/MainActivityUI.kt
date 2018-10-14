package com.example.andriginting.footballmatch.view

import com.example.andriginting.footballmatch.view.Main.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.relativeLayout

class MainActivityUI: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
           val recyclerFootbal = recyclerView {
                lparams(width = matchParent, height = matchParent)
            }
        }
    }
}