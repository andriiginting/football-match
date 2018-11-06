package com.example.andriginting.footballmatch.view.match.fav


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.ViewPagerAdapter
import com.example.andriginting.footballmatch.view.match.BasePagerView
import com.example.andriginting.footballmatch.view.teams.TeamFragment


class FavoriteFragment : Fragment(), BasePagerView {

    private lateinit var favTabs: TabLayout
    private lateinit var favPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        favTabs = view.findViewById(R.id.tablayout_fav)
        favPager = view.findViewById(R.id.viewpager_fav)

        pagerAdapter = ViewPagerAdapter(childFragmentManager)
        setupViewPager(favPager)
        favTabs.setupWithViewPager(favPager)
        return view
    }

    override fun setupViewPager(viewPager: ViewPager) {
        pagerAdapter.addFragment(FavoriteMatchFragment(),"Match")
        pagerAdapter.addFragment(FavTeamFragment(),"Team")
        favPager.adapter = pagerAdapter
    }
}
