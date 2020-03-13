package com.koma.movie.data.source

import com.koma.commonlibrary.data.entities.Result
import com.koma.movie.data.entities.Movie

interface MovieDataSource {
    suspend fun getPopularMovie(page: Int): Result<List<Movie>>

    suspend fun getTopRatedMovie(page: Int): Result<List<Movie>>

    suspend fun getNowPlayingMovie(page: Int): Result<List<Movie>>

    suspend fun getUpcomingMovie(page: Int): Result<List<Movie>>

    /**
     * Get a list of similar movies
     */
    suspend fun getSimilarMovie(movieId: Int): Result<List<Movie>>

    /**
     * Get a list of recommended movies for a movie.
     */
    suspend fun getRecommendedMovie(movieId: Int): Result<List<Movie>>
}