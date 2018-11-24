package com.example.andriginting.footballmatch.view.match


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager

import android.support.design.widget.TabLayout
import android.view.*

import com.example.andriginting.footballmatch.R
import com.example.andriginting.footballmatch.adapter.ViewPagerAdapter
import com.example.andriginting.footballmatch.view.match.next.NextMatchFragment
import com.example.andriginting.footballmatch.view.match.prev.PrevMatchFragment
import com.example.andriginting.footballmatch.view.match.result.ResultActivity

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
        setHasOptionsMenu(true)
        return view
    }

    override fun setupViewPager(viewPager: ViewPager) {
        pagerAdapter.addFragment(PrevMatchFragment(),"Prev Match")
        pagerAdapter.addFragment(NextMatchFragment(),"Next Match")
        pager.adapter = pagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search_item,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.search_menu -> {
                val intent = Intent(context, ResultActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }


}
