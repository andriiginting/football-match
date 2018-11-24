package com.example.andriginting.footballmatch.view.match.next

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
import com.example.andriginting.footballmatch.adapter.NextMatchAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.league.LeagueModel

class NextMatchFragment : Fragment(), NextContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinnerNextMatch: Spinner
    private var list: ArrayList<PrevMatchModel>? = null
    private var listLeague: ArrayList<LeagueModel> = ArrayList()
    private lateinit var listLeagueName: ArrayList<String>

    private lateinit var adapter: NextMatchAdapter
    private lateinit var presenter: ImpNextPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        activity?.actionBar?.title = getString(R.string.next_match)
        recyclerView = view.findViewById(R.id.recycler_next_match)
        progressBar = view.findViewById(R.id.progress_bar_next_match)
        spinnerNextMatch = view.findViewById(R.id.spinner_next_match)

        list = ArrayList()
        listLeagueName = ArrayList()

        presenter = ImpNextPresenter(this, list!!)
        presenter.getMatchDetail(4335)
        presenter.getListOfLeague(listLeague)
        adapter = context?.let { NextMatchAdapter(list!!, it) }!!

        initComponent()
        setupSpiner()
        return view
    }

    override fun initComponent() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun showLoadingIndicator() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hidLoadingIndicator() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showFootBallSchedule(data: ArrayList<PrevMatchModel>) {
        list = data
        adapter.notifyDataSetChanged()
    }
    override fun setSpinnerData(data: String) {
        listLeagueName.add(data)
    }
    override fun setupSpiner() {
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(activity!!.baseContext,
                android.R.layout.simple_spinner_item, listLeagueName)
        spinnerNextMatch.adapter = spinnerAdapter
        spinnerAdapter.add("Choose League")
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNextMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    list?.clear()
                    presenter.getMatchDetail(listLeague[position - 1].leagueId.toInt())
                    adapter.notifyDataSetChanged()
                }else{
                    list?.clear()
                }
            }

        }

    }
}
