package com.example.mymovie.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.R
import com.example.mymovie.ui.base.BaseActivity
import com.example.mymovie.ui.main.adapter.TabsPagerAdapter
import com.example.mymovie.ui.utils.showSnackbar
import com.example.mymovie.ui.utils.viewModelProvider
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

// Activity having tabs for both fragments ComingSoonFragment and NowShowingFragment
class MoviesActivity : BaseActivity() {
    override val contentView = R.layout.activity_movies

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var moviesViewModel: MoviesViewModel

    override fun viewModelSetup() {
        moviesViewModel = viewModelProvider(factory)
        initObservers()
        moviesViewModel.getMovies(1)
    }

    private fun initObservers() {
        moviesViewModel.errorLiveData.observe(this, Observer {
            it?.let { it1 -> toolbarDefault?.showSnackbar(it1) }
        })
    }

    override fun viewSetup() {
        val viewPagerAdapter = TabsPagerAdapter(supportFragmentManager)
        viewPager?.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(viewPager)
    }
}
