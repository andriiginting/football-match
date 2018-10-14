package com.example.andriginting.footballmatch.view.Main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.presenter.MainContract
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),MainContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
    }

    override fun initComponent() {
        recycler_football.layoutManager = LinearLayoutManager(this)

    }

}
