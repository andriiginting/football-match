package com.example.andriginting.footballmatch.view.match.fav


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.R.layout.no_matches_saved_layout
import com.example.andriginting.footballmatch.adapter.FavoriteTeamAdapter
import com.example.andriginting.footballmatch.adapter.TeamsAdapter
import com.example.andriginting.footballmatch.db.FootballTeamDB
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.teams.TeamModel

class FavTeamFragment : Fragment(), FavoriteTeamContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var noFavTeam: LinearLayout

    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var presenter: ImpFavoriteTeamFragment

    private var list: ArrayList<FootballTeamDB>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fav_team, container, false)
        recyclerView = view.findViewById(R.id.recycler_fav_team)
        progressBar = view.findViewById(R.id.progress_bar_fav_team)
        noFavTeam = view.findViewById(R.id.no_match_saved)

        list = ArrayList()
        adapter = FavoriteTeamAdapter(list!!)
        presenter = context?.let { ImpFavoriteTeamFragment(this, it) }!!
        presenter.getFavoriteTeam()
        initComponent()
        return view
    }

    override fun initComponent() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun showLoadingIndicator() {
        progressBar.visible()
    }

    override fun hideLoadingIndicator() {
        progressBar.invisible()
    }

    override fun showNoFavoriteTeam() {
        noFavTeam.visible()
    }

    override fun hideNoFavoriteTeam() {
        noFavTeam.invisible()
    }

    override fun showListFavTeam(data: List<FootballTeamDB>) {
        list?.clear()
        list?.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
