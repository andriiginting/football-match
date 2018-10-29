package com.example.andriginting.footballmatch.view.match

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment

interface MatchContract {
    interface View{
        fun addFragment(fragment: Fragment)

        fun removeShiftingBar(view: BottomNavigationView)
    }
}