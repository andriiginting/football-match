package com.example.andriginting.footballmatch.view.Main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.MainFootballAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.extension.withDelay
import com.example.andriginting.footballmatch.model.ClubModel
import com.example.andriginting.footballmatch.presenter.MainContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var adapter: MainFootballAdapter
    private var list: ArrayList<ClubModel> = ArrayList()
    private lateinit var presenter: ImpMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter = ImpMainPresenter(this, applicationContext, list)
        adapter = MainFootballAdapter(context = applicationContext,
                list = presenter.addClub())
        initComponent()
    }

    override fun initComponent() {
        recycler_football.layoutManager = LinearLayoutManager(this)
        recycler_football.adapter = adapter
    }

    override fun showLoadingIndicator() {
        progress_bar_main.visible()::apply{
            recycler_football.invisible()
        }
    }

    override fun hideLoadingIndicator() {
        progress_bar_main.invisible()
        recycler_football.visible()
    }
}
