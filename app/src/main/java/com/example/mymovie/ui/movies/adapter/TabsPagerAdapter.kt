package com.example.mymovie.ui.movies.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mymovie.R

import com.example.mymovie.ui.movies.ComingSoonFragment
import com.example.mymovie.ui.movies.NowShowingFragment

//Adapter for managing tabs
class TabsPagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    //Num of Tab Items

    companion object {
        const val ITEM_COUNT = 2
    }

    //array having titles of Tab
    private val titles = arrayOf(context.getString(R.string.now_showing), context.getString(R.string.coming_soon))

    // Returns total number of pages
    override fun getCount(): Int {
        return ITEM_COUNT
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