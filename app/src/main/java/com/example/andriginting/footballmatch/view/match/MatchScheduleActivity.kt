package com.example.andriginting.footballmatch.view.match

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.view.match.fav.FavoriteFragment
import com.example.andriginting.footballmatch.view.teams.TeamFragment

import kotlinx.android.synthetic.main.activity_match_schedule.*
import kotlinx.android.synthetic.main.content_match_schedule.*

class MatchScheduleActivity : AppCompatActivity(), MatchContract.View {
    private var lastSelected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_schedule)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_navigation.selectedItemId = R.id.tab_match
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (item.itemId == lastSelected) {
            return@OnNavigationItemSelectedListener true
        }
        when (item.itemId) {
            R.id.tab_match -> {
                val fragment = BaseMatchFragment()
                addFragment(fragment)
                lastSelected = item.itemId
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_team -> {
                val fragment = TeamFragment()
                addFragment(fragment)
                lastSelected = item.itemId
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_fav_match -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                lastSelected = item.itemId
                return@OnNavigationItemSelectedListener true
            }
        }
        true
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, fragment, fragment.javaClass.simpleName)
                .commit()

    }

    @SuppressLint("RestrictedApi")
    override fun removeShiftingBar(view: BottomNavigationView) {
        val menuView: BottomNavigationMenuView =
                view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShifting(false)
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFileException) {
            throw Exception("Class Unknown")
        }

    }
}
