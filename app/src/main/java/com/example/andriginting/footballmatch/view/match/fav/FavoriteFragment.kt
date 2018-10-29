package com.example.andriginting.footballmatch.view.match.fav


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.FavoriteMatchAdapter
import com.example.andriginting.footballmatch.db.FootballDB
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible

class FavoriteFragment : Fragment(), FavoriteContract.View {


    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var noFavMatch: LinearLayout

    private lateinit var list: ArrayList<FootballDB>
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var presenter: ImpFavoritePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        activity?.actionBar?.title = getString(R.string.fav_match_title)
        recyclerView = view.findViewById(R.id.recycler_fav_match)
        progressBar = view.findViewById(R.id.progress_bar_fav)
        noFavMatch = view.findViewById(R.id.no_match_saved)

        list = ArrayList()
        adapter = context?.let { FavoriteMatchAdapter(list, it) }!!
        presenter = ImpFavoritePresenter(this, context!!)

        presenter.getFavoriteMatch()
        initComponent()
        return view
    }

    override fun initComponent() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val itemDecorator = DividerItemDecoration(context, RecyclerView.VERTICAL)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(itemDecorator)
    }

    override fun showLoadingIndicator() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoadingIndicator() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showListFavMatch(data: List<FootballDB>) {
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showNoFavoriteMatch() {
        noFavMatch.visible()
        recyclerView.invisible()
    }

    override fun hideNoFavoriteMatch() {
        noFavMatch.invisible()
        recyclerView.visible()
    }
}
