package com.example.andriginting.footballmatch.view.match.next


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.NextMatchAdapter
import com.example.andriginting.footballmatch.extension.invisible
import com.example.andriginting.footballmatch.extension.visible
import com.example.andriginting.footballmatch.model.PrevMatchModel

class NextMatchFragment : Fragment(), NextContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var list: ArrayList<PrevMatchModel>? = null

    private lateinit var adapter: NextMatchAdapter
    private lateinit var presenter: ImpNextPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        activity?.actionBar?.title = getString(R.string.next_match)
        recyclerView = view.findViewById(R.id.recycler_next_match)
        progressBar = view.findViewById(R.id.progress_bar_next_match)

        list = ArrayList()

        presenter = ImpNextPresenter(this, list!!)
        presenter.getMatchDetail(4335)
        adapter = context?.let { NextMatchAdapter(list!!, it) }!!

        initComponent()
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
}
