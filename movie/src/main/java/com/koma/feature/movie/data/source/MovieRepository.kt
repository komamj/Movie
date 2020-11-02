/*
 * Copyright 2020 komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.koma.feature.movie.data.source

import com.koma.common.data.entities.Resource
import com.koma.database.data.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovie(
        page: Int,
        forceUpdate: Boolean = false
    ): Flow<Resource<List<Movie>>>

    suspend fun getTopRatedMovie(page: Int, forceUpdate: Boolean = false): Resource<List<Movie>>

    suspend fun getNowPlayingMovie(page: Int, forceUpdate: Boolean = false): Resource<List<Movie>>

    suspend fun getUpcomingMovie(page: Int, forceUpdate: Boolean = false): Resource<List<Movie>>

    /**
     * Get a list of similar movies
     */
    suspend fun getSimilarMovie(movieId: Int, forceUpdate: Boolean = false): Resource<List<Movie>>

    /**
     * Get a list of recommended movies for a movie.
     */
    suspend fun getRecommendedMovie(
        movieId: Int,
        forceUpdate: Boolean = false
    ): Resource<List<Movie>>

    suspend fun getImages(movieId: Int)

    suspend fun postRatingMovie(movieId: Int)

    suspend fun searchMovie(
        keyword: String,
        includeAdult: Boolean = true
    ): Resource<List<Movie>>
}
