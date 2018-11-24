package com.example.andriginting.footballmatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.example.andriginting.footballmatch.R.id.*
import com.example.andriginting.footballmatch.view.match.MatchScheduleActivity
import org.junit.Rule
import org.junit.Test

class FootballMatchUISpec {
    @Rule
    @JvmField
    var rules = ActivityTestRule(MatchScheduleActivity::class.java)


    @Test
    fun testFavMatch() {
        Thread.sleep(3000)
        onView(withId(R.id.contentContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_fav_match)).perform(click())
        onView(withId(R.id.recycler_fav_match)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler_fav_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.menu_fav)).perform(click())
        pressBack()
    }

    @Test
    fun testFavButton(){
        Thread.sleep(3000)
        onView(withId(R.id.contentContainer)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_fav_match)).perform(click())
        onView(withId(R.id.recycler_fav_match)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler_fav_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.menu_fav)).perform(click())
        pressBack()

        onView(withId(R.id.recycler_fav_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.menu_fav)).perform(click())
    }

}