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

package com.koma.movie.data.source.local

import com.koma.commonlibrary.data.entities.Result
import com.koma.movie.data.entities.Movie
import com.koma.movie.data.source.MovieDataSource
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val movieDao: MovieDao
) : MovieDataSource {
    override suspend fun getPopularMovie(page: Int): Result<List<Movie>> =
        withContext(context = Dispatchers.IO) {
            return@withContext try {
                val movieList = movieDao.getMovie(page)
                Result.Success(movieList)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getTopRatedMovie(page: Int): Result<List<Movie>> =
        withContext(context = Dispatchers.IO) {
            return@withContext try {
                val movieList = movieDao.getMovie(page)
                Result.Success(movieList)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getNowPlayingMovie(page: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovie(page: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    /**
     * Get a list of similar movies
     */
    override suspend fun getSimilarMovie(movieId: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    /**
     * Get a list of recommended movies for a movie.
     */
    override suspend fun getRecommendedMovie(movieId: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    suspend fun saveMovie(movie: List<Movie>) = withContext(context = Dispatchers.IO) {
        movieDao.insert(movie)
    }
}
