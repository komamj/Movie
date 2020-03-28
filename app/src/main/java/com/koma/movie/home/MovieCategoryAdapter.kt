package com.koma.movie.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.koma.movie.R
import com.koma.movie.databinding.ItemMovieCategoryBinding

class MovieCategoryAdapter :
    MovieAdapter<MovieCategoryAdapter.MovieCategoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryVH {
        return MovieCategoryVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieCategoryVH, position: Int) {
        bind(holder, position)
    }

    private fun bind(viewHolder: MovieCategoryVH, position: Int) {
        val movie = getItem(position)
        viewHolder.binding.movie = movie
        viewHolder.binding.clickListener = createOnClickListener(movie)
        viewHolder.binding.executePendingBindings()
    }

    class MovieCategoryVH(val binding: ItemMovieCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}