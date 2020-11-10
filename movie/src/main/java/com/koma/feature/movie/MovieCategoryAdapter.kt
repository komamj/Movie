package com.koma.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koma.database.data.entities.Movie
import com.koma.feature.movie.databinding.MovieItemMovieHorizontalBinding

class MovieCategoryAdapter :
    ListAdapter<Movie, MovieCategoryAdapter.MovieCategoryVH>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryVH {
        return MovieCategoryVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item_movie_horizontal,
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

    private fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            // todo launch detail page.
        }
    }

    class MovieCategoryVH(val binding: MovieItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
