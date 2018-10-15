package com.example.andriginting.footballmatch.view.match

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.andriginting.footballmatch.R

import kotlinx.android.synthetic.main.activity_match_schedule.*

class MatchScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_schedule)
        setSupportActionBar(toolbar)

    }

}
