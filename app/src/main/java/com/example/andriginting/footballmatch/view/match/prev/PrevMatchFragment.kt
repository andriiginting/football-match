package com.example.andriginting.footballmatch.view.match.prev


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.PreviousMatchAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel
import com.example.andriginting.footballmatch.model.teams.TeamDetailModel

class PrevMatchFragment : Fragment(), PrevContract.View {

    private lateinit var progressbar: ProgressBar
    private lateinit var recyclerPrevMatch: RecyclerView

    private var presenter: ImpPreviousPresenter? = null
    private var adapter: PreviousMatchAdapter? = null

    private var list: ArrayList<PrevMatchModel>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prev_match, container, false)
        activity?.actionBar?.title = getString(R.string.prev_match)
        recyclerPrevMatch = view.findViewById(R.id.recycler_past_match)
        progressbar = view.findViewById(R.id.progress_bar_prev_match)

        list = ArrayList()
        presenter = ImpPreviousPresenter(this, list = list!!)

        adapter = context?.let {
            PreviousMatchAdapter(list = presenter?.getMatchDetail(4335)!!, context = it)
        }
        initComponent()
        return view
    }

    override fun initComponent() {
        recyclerPrevMatch.layoutManager = LinearLayoutManager(context)
        recyclerPrevMatch.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadingIndicator() {
        progressbar.visible()
        recyclerPrevMatch.invisible()
    }

    override fun hidLoadingIndicator() {
        progressbar.invisible()
        recyclerPrevMatch.visible()
    }

    override fun showFootBallSchedule(data: ArrayList<PrevMatchModel>) {
        list?.clear()
        list = data
        adapter?.notifyDataSetChanged()
    }
}
