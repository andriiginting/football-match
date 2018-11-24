package com.example.andriginting.footballmatch

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.example.andriginting.footballmatch.view.match.MatchScheduleActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test


class TeamFragmentSpec {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun testSpinnerTeam() {
        Thread.sleep(3000)
        onView(withId(R.id.tab_team)).perform(click())
        onView(withId(R.id.spinner_team)).perform(click())
    }

    @Test
    fun testSpinnerSelectPosition() {
        Thread.sleep(3000)
        onView(withId(R.id.tab_team)).perform(click())
        onView(withId(R.id.spinner_team)).perform(click())
    }

    @Test
    fun testSelectTeam() {
        Thread.sleep(3000)
        onView(withId(R.id.tab_team)).perform(click())
        onView(withId(R.id.search_menu)).perform(click())
        onView(withId(R.id.search_src_text)).perform(typeText("barcelona"))
        Thread.sleep(3000)
        onView(withId(R.id.recycler_teams)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler_teams)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(withId(R.id.menu_fav)).perform(click())

        onView(withId(R.id.viewpager_team_detail)).check(matches(isDisplayed()))
    }

}