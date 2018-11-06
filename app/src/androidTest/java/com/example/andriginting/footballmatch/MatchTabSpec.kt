package com.example.andriginting.footballmatch

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.example.andriginting.footballmatch.view.match.MatchScheduleActivity
import org.junit.Rule
import org.junit.Test

class MatchTabSpec {

    @Rule
    @JvmField
    var rules = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun testMatchTab() {
        Thread.sleep(3000)
        onView(withId(R.id.contentContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_match)).perform(click())
    }

    @Test
    fun testSwipePager(){
        onView(withId(R.id.viewpager_match)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.viewpager_match)).perform(swipeLeft())
        Thread.sleep(3000)
        onView(withId(R.id.viewpager_match)).perform(swipeRight())
    }
}