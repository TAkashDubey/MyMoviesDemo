package com.example.mymovie.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.example.mymovie.ui.main.ComingSoonFragment
import com.example.mymovie.ui.main.NowShowingFragment
//Adapter for managing tabs
class TabsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //Num of Tab Items
    private val NUM_ITEMS = 2

    //array having titles of Tab
    private val titles = arrayOf("Now Showing", "Comming Soom")

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            NowShowingFragment.newInstance()
        } else {
            ComingSoonFragment.newInstance()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}