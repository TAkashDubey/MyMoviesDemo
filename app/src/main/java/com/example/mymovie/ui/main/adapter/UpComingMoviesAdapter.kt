package com.example.mymovie.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.data.remote.response.Upcoming
import com.example.mymovie.databinding.ItemMoviesComingSoonBinding

class UpComingMoviesAdapter(var items: MutableList<Upcoming?>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var isLoading = false

    fun showProgress() {
        isLoading = true
    }

    fun hideProgress() {
        isLoading = false
    }

    override fun getItemCount() = if (isLoading) items?.size?.plus(1) ?: 0 else items?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return if (position == items?.size && isLoading) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val inflater = LayoutInflater.from(parent.context)
                return UpComingMoviesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_movies_coming_soon, parent, false))
            }
            VIEW_TYPE_LOADING -> {
                val inflater = LayoutInflater.from(parent.context)
                LoadingViewHolder(inflater.inflate(R.layout.item_footer_progress, parent, false))
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                return UpComingMoviesViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_movies_coming_soon, parent, false))
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UpComingMoviesViewHolder)
            holder.bind(position)
    }

    inner class UpComingMoviesViewHolder(private val itemMoviesBinding: ItemMoviesComingSoonBinding) : RecyclerView.ViewHolder(itemMoviesBinding.root) {
        fun bind(position: Int) {
            if (items?.isNotEmpty()!!)
                if (position != 0) {
                    itemMoviesBinding.model = items?.get(position - 1)
                } else {
                    itemMoviesBinding.model = items?.get(position)
                }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBar)
        }
    }
}