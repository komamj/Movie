package com.koma.movie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koma.movie.data.entities.Movie
import com.koma.movie.home.MovieCategoryAdapter

@BindingAdapter("movieList")
fun bindMovieList(recyclerView: RecyclerView, movieList: List<Movie>) {
    if (recyclerView.adapter == null) {
        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(recyclerView.context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            itemAnimator = DefaultItemAnimator()
            adapter = MovieCategoryAdapter()
        }
    }
    if (recyclerView.adapter is MovieCategoryAdapter) {
        (recyclerView.adapter as MovieCategoryAdapter).submitList(movieList)
    }
}