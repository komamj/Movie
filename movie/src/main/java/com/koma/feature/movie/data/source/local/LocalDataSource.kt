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

package com.koma.feature.movie.data.source.local

import com.koma.common.data.entities.Result
import com.koma.database.data.entities.Movie
import com.koma.feature.movie.data.source.MovieDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val movieDao: MovieDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDataSource {
    override suspend fun getPopularMovie(page: Int): Result<List<Movie>> =
        withContext(dispatcher) {
            return@withContext try {
                val movieList = movieDao.getMovie(page)
                Result.Success(movieList)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getTopRatedMovie(page: Int): Result<List<Movie>> =
        withContext(dispatcher) {
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

    suspend fun saveMovie(movie: List<Movie>) = withContext(dispatcher) {
        movieDao.insert(movie)
    }
}
