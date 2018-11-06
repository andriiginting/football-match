package com.example.andriginting.footballmatch.view.detail.teamdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.ListPlayerAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.player.PlayerModel
import com.example.andriginting.footballmatch.model.teams.TeamModel

class ListPlayerFragment : Fragment(), ListPlayerContract.View {


    private lateinit var listAdapter: ListPlayerAdapter

    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar

    private var list: ArrayList<PlayerModel> = ArrayList()
    private var presenter: ImpListPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_player, container, false)

        recycler = view.findViewById(R.id.recycler_list_player)
        progress = view.findViewById(R.id.progress_bar_list_player)
        presenter = ImpListPlayer(this)
        val getData = activity?.intent
                ?.getParcelableExtra<TeamModel>(TeamDetailActivity.TEAM_DETAIL)

        presenter!!.getListPlayer(teamId = getData?.teamId!!.toInt(), list = list)
        listAdapter = ListPlayerAdapter(list)
        initComponent()
        return view
    }

    override fun initComponent() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = listAdapter
    }

    override fun populatePlayerData(data: ArrayList<PlayerModel>) {
        list = data
        listAdapter.notifyDataSetChanged()
    }

    override fun showLoadingIndicator() {
        progress.visible()
    }

    override fun hideLoadingIndicator() {
        progress.invisible()
    }
}
