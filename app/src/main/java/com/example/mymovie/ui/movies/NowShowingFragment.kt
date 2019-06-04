package com.example.mymovie.ui.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.mymovie.domain.entity.MovieEntity
import com.example.mymovie.ui.base.BaseFragment
import com.example.mymovie.ui.movies.adapter.NowShowingMoviesAdapter
import com.example.mymovie.ui.utils.EndlessRecyclerOnScrollListener
import com.example.mymovie.ui.utils.activityViewModelProvider
import com.example.mymovie.ui.utils.gone
import com.example.mymovie.ui.utils.showSnackbar
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

    private val nowShowingMoviesAdapter: NowShowingMoviesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NowShowingMoviesAdapter(moviesViewModel.nowShowingMovieLiveData.value)
    }

    override fun viewModelSetup() {
        moviesViewModel = activityViewModelProvider(viewModelFactory)
        initObservers()
    }

    private fun initObservers() {
        moviesViewModel.errorLiveData.observe(this, Observer { errorString ->
            progressBar?.gone()
            errorString?.let { it -> progressBar?.showSnackbar(it) }
        })
        moviesViewModel.nowShowingMovieLiveData.observe(this, Observer {
            progressBar?.gone()
            if (nowShowingMoviesAdapter.items == null || nowShowingMoviesAdapter.items?.size == 0) {
                setNowShowingMovies(it)
            } else {
                addNowShowingMovies(it)
            }
            if (swipeRefresh?.isRefreshing == true) {
                swipeRefresh?.isRefreshing = false
            }
        })
    }

    private fun setNowShowingMovies(newList: MutableList<MovieEntity.MovieDataEntity>?) {
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

    private fun addNowShowingMovies(newList: MutableList<MovieEntity.MovieDataEntity>?) {
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