package com.example.mymovie.ui.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.data.remote.response.Showing
import com.example.mymovie.ui.base.BaseFragment
import com.example.mymovie.ui.main.adapter.NowShowingMoviesAdapter
import com.example.mymovie.ui.utils.EndlessRecyclerOnScrollListener
import com.example.mymovie.ui.utils.activityViewModelProvider
import com.example.mymovie.ui.utils.gone
import kotlinx.android.synthetic.main.fragment_now_showing.*
import javax.inject.Inject

class NowShowingFragment : BaseFragment() {

    companion object {
        fun newInstance(): NowShowingFragment {
            return NowShowingFragment()
        }
    }

    override val contentView = R.layout.fragment_now_showing

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var moviesViewModel: MoviesViewModel

    private val nowShowingMoviesAdapter: NowShowingMoviesAdapter by lazy {
        NowShowingMoviesAdapter(moviesViewModel.nowShowingMovieLiveData.value ?: mutableListOf())
    }

    override fun viewModelSetup() {
        moviesViewModel = activityViewModelProvider(viewModelFactory)
        moviesViewModel.nowShowingMovieLiveData.observe(this, Observer {
            if (nowShowingMoviesAdapter.items?.size == 0) {
                setNowShowingMovies(it)
            } else {
                addNowShowingMovies(it)
            }
            if (swipeRefresh?.isRefreshing == true) {
                swipeRefresh?.isRefreshing = false
            }
        })
    }

    private fun setNowShowingMovies(newList: MutableList<Showing?>?) {
        progressBar?.gone()
        nowShowingMoviesAdapter.items = newList
        nowShowingMoviesAdapter.notifyDataSetChanged()
        rvNowShowing?.layoutManager?.let {
            rvNowShowing?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(it as LinearLayoutManager) {
                override fun onLoadMore(current_page: Int) {
                    nowShowingMoviesAdapter.showProgress()
                    moviesViewModel.getMovies(current_page)
                }
            })
        }
    }

    private fun addNowShowingMovies(newList: MutableList<Showing?>?) {
        nowShowingMoviesAdapter.hideProgress()
        newList?.let { nowShowingMoviesAdapter.items?.addAll(it) }
        nowShowingMoviesAdapter.notifyDataSetChanged()
    }

    override fun viewSetup() {
        rvNowShowing?.adapter = nowShowingMoviesAdapter
        swipeRefresh?.setOnRefreshListener {
            nowShowingMoviesAdapter.items?.clear()
            nowShowingMoviesAdapter.notifyDataSetChanged()
            moviesViewModel.getMovies(0)
        }
    }
}