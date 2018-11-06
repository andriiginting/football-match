package com.example.andriginting.footballmatch.view.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.support.design.widget.TabLayout;

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.ViewPagerAdapter
import com.example.andriginting.footballmatch.view.match.next.NextMatchFragment
import com.example.andriginting.footballmatch.view.match.prev.PrevMatchFragment

class BaseMatchFragment : Fragment(), BasePagerView {


    private lateinit var tabs: TabLayout
    private lateinit var pager: ViewPager

    private lateinit var pagerAdapter: ViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_match, container, false)

        tabs = view.findViewById(R.id.tablayout_match)
        pager = view.findViewById(R.id.viewpager_match)

        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        setupViewPager(pager)
        tabs.setupWithViewPager(pager)
        return view
    }

    override fun setupViewPager(viewPager: ViewPager) {
        pagerAdapter.addFragment(PrevMatchFragment(),"Prev Match")
        pagerAdapter.addFragment(NextMatchFragment(),"Next Match")
        pager.adapter = pagerAdapter
    }

}
