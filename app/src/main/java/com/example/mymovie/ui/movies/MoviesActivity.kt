package com.example.mymovie.ui.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.R
import com.example.mymovie.ui.base.BaseActivity
import com.example.mymovie.ui.movies.adapter.TabsPagerAdapter
import com.example.mymovie.ui.utils.gone
import com.example.mymovie.ui.utils.showSnackbar
import com.example.mymovie.ui.utils.viewModelProvider
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.fragment_coming_soon.*
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
    }

    private fun initObservers() {

    }

    override fun viewSetup() {
        val viewPagerAdapter = TabsPagerAdapter(supportFragmentManager, this)
        vpMovies?.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(vpMovies)
        moviesViewModel.getMovies(1)
    }
}
