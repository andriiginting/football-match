package com.example.andriginting.footballmatch.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    private var listFragment: ArrayList<Fragment> = ArrayList()
    private val listTitle: ArrayList<String> = ArrayList()

    override fun getItem(p0: Int): Fragment = listFragment[p0]

    override fun getCount(): Int = listFragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }

    fun addFragment(fragment: Fragment, title: String){
        listFragment.add(fragment)
        listTitle.add(title)
    }
}