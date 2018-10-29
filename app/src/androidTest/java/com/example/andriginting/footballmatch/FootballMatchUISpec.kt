package com.example.andriginting.footballmatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.example.andriginting.footballmatch.R.id.*
import com.example.andriginting.footballmatch.R.layout.activity_detail_match
import com.example.andriginting.footballmatch.R.layout.fragment_prev_match
import com.example.andriginting.footballmatch.view.Main.MainActivity
import com.example.andriginting.footballmatch.view.match.MatchScheduleActivity
import com.example.andriginting.footballmatch.view.match.detail.DetailMatchActivity
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.junit.Rule
import org.junit.Test
import org.mockito.AdditionalMatchers.not

class FootballMatchUISpec {
    @Rule
    @JvmField
    var rules = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun testMainRecyclerView() {
        Thread.sleep(3000)
        onView(withId(recycler_football)).check(matches(isDisplayed()))

        onView(withId(recycler_football)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }

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