package com.example.andriginting.footballmatch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.example.andriginting.footballmatch.view.match.MatchScheduleActivity
import org.junit.Rule
import org.junit.Test


class TeamFragmentSpec {
    @Rule
    @JvmField
    var rule = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun testSpinnerTeam(){
        Thread.sleep(3000)
        onView(withId(R.id.tab_team)).perform(click())
        onView(withId(R.id.spinner_team)).perform(click())
    }

    @Test
    fun testSpinnerSelectPosition(){
        Thread.sleep(3000)
        onView(withId(R.id.tab_team)).perform(click())
        onView(withId(R.id.spinner_team)).perform(click())
    }
}