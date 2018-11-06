package com.example.andriginting.footballmatch.view.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.TeamsAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel

class TeamFragment : Fragment(), TeamContract.View {

    private lateinit var recycler: RecyclerView
    private lateinit var spinnerLeague: Spinner
    private lateinit var progbar: ProgressBar

    private var adapter: TeamsAdapter? = null
    private lateinit var listTeam: ArrayList<TeamModel>
    private lateinit var listLeague: ArrayList<LeagueModel>
    private lateinit var listLeagueName: ArrayList<String>

    private lateinit var presenter: ImpTeamPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_team, container, false)
        recycler = view.findViewById(R.id.recycler_teams)
        spinnerLeague = view.findViewById(R.id.spinner_team)
        progbar = view.findViewById(R.id.progress_bar_teams)

        presenter = ImpTeamPresenter(this)

        listTeam = ArrayList()
        listLeague = ArrayList()
        listLeagueName = ArrayList()

        presenter.getListOfLeagueTeam(listLeague)

        setupSpinner()
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {

                } else {
                    listTeam = presenter.getListOfTeam(listLeague[position].leagueId.toInt(), listTeam)
                }
                adapter?.notifyDataSetChanged()
            }
        }
        adapter = TeamsAdapter(presenter.getListOfTeam(4328, listTeam))

        setupComponent()

        return view
    }

    override fun setupSpinner() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(context,
                android.R.layout.simple_spinner_item, listLeagueName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeague.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun setupComponent() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun hideLoadingIndicator() {
        progbar.invisible()
    }

    override fun showLoadingIndicator() {
        progbar.visible()
    }

    override fun setSpinnerData(data: String) {
        listLeagueName.add(data)
    }

    override fun populateTeamData(data: TeamModel) {
        listTeam.clear()
        listTeam.add(data)
        adapter?.notifyDataSetChanged()
    }
}
