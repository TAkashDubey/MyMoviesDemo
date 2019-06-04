package com.example.mymovie.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.data.remote.response.Upcoming
import com.example.mymovie.ui.base.BaseFragment
import com.example.mymovie.ui.main.adapter.UpComingMoviesAdapter
import com.example.mymovie.ui.utils.EndlessRecyclerOnScrollListener
import com.example.mymovie.ui.utils.activityViewModelProvider
import com.example.mymovie.ui.utils.gone
import kotlinx.android.synthetic.main.fragment_coming_soon.*
import javax.inject.Inject

class ComingSoonFragment : BaseFragment() {

    companion object {
        fun newInstance(): ComingSoonFragment {
            return ComingSoonFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var moviesViewModel: MoviesViewModel

    override val contentView = R.layout.fragment_coming_soon

    private val upComingMoviesAdapter: UpComingMoviesAdapter by lazy {
        UpComingMoviesAdapter(moviesViewModel.upComingMovieLiveData.value ?: mutableListOf())
    }

    override fun viewModelSetup() {
        moviesViewModel = activityViewModelProvider(viewModelFactory)
        moviesViewModel.upComingMovieLiveData.observe(this, Observer {
            if (upComingMoviesAdapter.items?.size == 0) {
                setUpcomingMovies(it)
            } else {
                addUpcomingMovies(it)
            }
            if (swipeRefresh?.isRefreshing == true) {
                swipeRefresh?.isRefreshing = false
            }
        })
    }

    private fun setUpcomingMovies(newList: MutableList<Upcoming?>?) {
        progressBar?.gone()
        upComingMoviesAdapter.items = newList
        upComingMoviesAdapter.notifyDataSetChanged()
        rvComingSoon?.layoutManager?.let {
            rvComingSoon?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(it as LinearLayoutManager) {
                override fun onLoadMore(current_page: Int) {
                    upComingMoviesAdapter.showProgress()
                    moviesViewModel.getMovies(current_page)
                }
            })
        }
    }

    private fun addUpcomingMovies(newList: MutableList<Upcoming?>?) {
        upComingMoviesAdapter.hideProgress()
        newList?.let { upComingMoviesAdapter.items?.addAll(it) }
        upComingMoviesAdapter.notifyDataSetChanged()
    }

    override fun viewSetup() {
        rvComingSoon?.adapter = upComingMoviesAdapter
        swipeRefresh?.setOnRefreshListener {
            upComingMoviesAdapter.items?.clear()
            upComingMoviesAdapter.notifyDataSetChanged()
            moviesViewModel.getMovies(0)
        }
    }
}