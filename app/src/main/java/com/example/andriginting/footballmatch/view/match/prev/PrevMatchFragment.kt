package com.example.andriginting.footballmatch.view.match.prev


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.PreviousMatchAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.league.LeagueModel

class PrevMatchFragment : Fragment(), PrevContract.View {

    private lateinit var progressbar: ProgressBar
    private lateinit var recyclerPrevMatch: RecyclerView
    private lateinit var spinnerPreMatch: Spinner

    private var presenter: ImpPreviousPresenter? = null
    private var adapter: PreviousMatchAdapter? = null

    private var list: ArrayList<PrevMatchModel> = ArrayList()
    private var listLeague: ArrayList<LeagueModel> = ArrayList()
    private var listLeagueName: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prev_match, container, false)

        activity?.actionBar?.title = getString(R.string.prev_match)
        recyclerPrevMatch = view.findViewById(R.id.recycler_past_match)
        progressbar = view.findViewById(R.id.progress_bar_prev_match)
        spinnerPreMatch = view.findViewById(R.id.spinner_prev_match)

        presenter = ImpPreviousPresenter(this)

        list = presenter?.getMatchDetail(4335,list)!!
        presenter!!.getListOfLeagueTeam(listLeague)
        adapter = context?.let {
            PreviousMatchAdapter(list = list, context = it)
        }

        initComponent()
        setupSpinner()
        return view
    }

    override fun initComponent() {
        recyclerPrevMatch.layoutManager = LinearLayoutManager(context)
        recyclerPrevMatch.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadingIndicator() {
        progressbar.visible()
    }

    override fun hidLoadingIndicator() {
        progressbar.invisible()
    }

    override fun showFootBallSchedule(data: ArrayList<PrevMatchModel>) {
        list = data
        adapter?.notifyDataSetChanged()
    }

    private fun setupSpinner() {
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(activity!!.baseContext,
                android.R.layout.simple_spinner_item, listLeagueName)
        spinnerPreMatch.adapter = spinnerAdapter
        spinnerAdapter.add("Choose League")
        spinnerPreMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    list.clear()
                    presenter?.getMatchDetail(listLeague[position - 1].leagueId.toInt(),list)
                    adapter?.notifyDataSetChanged()
                }
            }

        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun setSpinnerData(data: String) {
        listLeagueName.add(data)
    }
}
