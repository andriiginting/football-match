package com.example.andriginting.footballmatch.view.detail.teamdetail


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.view.detail.teamdetail.TeamDetailActivity.Companion.TEAM_DETAIL

class OverviewFragment : Fragment(){

    lateinit var overview: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        overview = view.findViewById(R.id.text_overview)
        val getData = activity?.intent?.getParcelableExtra<TeamModel>(TEAM_DETAIL)
        overview.text = getData?.clubDescriptions
        return view
    }
}
