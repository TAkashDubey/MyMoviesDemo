package com.example.mymovie.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.databinding.ItemMoviesNowShowingBinding
import com.example.mymovie.domain.entity.MovieEntity

class NowShowingMoviesAdapter(var items: MutableList<MovieEntity.MovieDataEntity>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    private var isLoading = false

    override fun getItemCount() = if (isLoading) items?.size?.plus(1) ?: 0 else items?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return if (position == items?.size && isLoading) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun showProgress() {
        isLoading = true
    }

    fun hideProgress() {
        isLoading = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val inflater = LayoutInflater.from(parent.context)
                NowShowingMoviesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_movies_now_showing, parent, false))
            }
            VIEW_TYPE_LOADING -> {
                val inflater = LayoutInflater.from(parent.context)
                LoadingViewHolder(inflater.inflate(R.layout.item_footer_progress, parent, false))
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                NowShowingMoviesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_movies_now_showing, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NowShowingMoviesViewHolder) {
            holder.bind(position)
        }
    }

    inner class NowShowingMoviesViewHolder(private val itemMoviesBinding: ItemMoviesNowShowingBinding) : RecyclerView.ViewHolder(itemMoviesBinding.root) {
        fun bind(position: Int) {
            if (items?.isNotEmpty()!!)
                if (position != 0) {
                    itemMoviesBinding.model = items?.get(position - 1)
                } else {
                    itemMoviesBinding.model = items?.get(position)
                }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}