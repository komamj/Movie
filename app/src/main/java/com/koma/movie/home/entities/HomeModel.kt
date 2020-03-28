package com.koma.movie.home.entities

import com.koma.movie.data.entities.Movie

data class HomeModel(
    val title: String,
    val description: String,
    val movieList: List<Movie>?
) {
}