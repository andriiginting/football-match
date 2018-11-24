package com.example.andriginting.footballmatch.view.teams


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import android.support.v7.widget.SearchView
import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.TeamsAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.league.LeagueModel
import com.example.andriginting.footballmatch.model.teams.TeamModel
import com.example.andriginting.footballmatch.model.teams.TeamResponse
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Response

import java.util.concurrent.TimeUnit

class TeamFragment : Fragment(), TeamContract.View {
    private lateinit var recycler: RecyclerView
    private lateinit var spinnerLeague: Spinner
    private lateinit var progbar: ProgressBar

    private var teamAdapter: TeamsAdapter? = null
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
        teamAdapter = TeamsAdapter(listTeam)

        setupComponent()
        setupSpinner()
        setHasOptionsMenu(true)
        return view
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.search_menu)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.find_team)

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    return false
                }
            })
        })
                .map { text -> text.toLowerCase().trim() }
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter { text -> text.isNotBlank() }
                .switchMapSingle(Function<String, Single<Response<TeamResponse>>> {query ->
                    listTeam.clear()
                    return@Function presenter.searchTeam(query)
                })
                .subscribeWith(presenter.getSearchObserver())

    }

    override fun setupSpinner() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(activity!!.baseContext,
                android.R.layout.simple_spinner_item, listLeagueName)
        spinnerLeague.adapter = adapter
        adapter.add("Choose league")
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    listTeam.clear()
                    presenter.getListOfTeam(listLeague[position - 1].leagueId.toInt(), listTeam)
                    teamAdapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun setupComponent() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = teamAdapter
        teamAdapter?.notifyDataSetChanged()
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

    override fun populateTeamData(data: List<TeamModel>) {
        listTeam.addAll(data)
        teamAdapter?.notifyDataSetChanged()
    }
}
