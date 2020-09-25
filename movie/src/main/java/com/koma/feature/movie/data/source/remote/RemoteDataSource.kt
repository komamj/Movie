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

package com.koma.feature.movie.data.source.remote

import com.koma.common.data.entities.Resource
import com.koma.database.data.entities.Movie
import com.koma.feature.movie.data.source.MovieDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webService: WebService) : MovieDataSource {
    override suspend fun getPopularMovie(page: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getPopularMovie(page).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getTopRatedMovie(page: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getTopRatedMovie(page).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getNowPlayingMovie(page: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getNowPlayingMovie(page).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getUpcomingMovie(page: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getUpcomingMovie(page).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    /**
     * Get a list of similar movies
     */
    override suspend fun getSimilarMovie(movieId: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getSimilarMovie(movieId).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    /**
     * Get a list of recommended movies for a movie.
     */
    override suspend fun getRecommendedMovie(movieId: Int): Resource<List<Movie>> {
        return try {
            val movieList = webService.getRecommendedMovie(movieId).data
            Resource.Success(movieList)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    suspend fun getImages(movieId: Int) {
        TODO("Not yet implemented")
    }

    suspend fun postRatingMovie(movieId: Int) {
        TODO("Not yet implemented")
    }

    suspend fun searchMovie(keyword: String, includeAdult: Boolean): Resource<List<Movie>> {
        TODO("Not yet implemented")
    }
}
