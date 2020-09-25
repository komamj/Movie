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

import com.koma.network.data.entities.DataModel
import com.koma.database.data.entities.Movie
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int = 1): DataModel<Movie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("page") page: Int = 1): DataModel<Movie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("page") page: Int = 1): DataModel<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("page") page: Int = 1): DataModel<Movie>

    /**
     * Get a list of similar movies
     */
    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(@Path("movie_id") movieId: Int): DataModel<Movie>

    /**
     * Get a list of recommended movies for a movie.
     */
    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovie(@Path("movie_id") movieId: Int): DataModel<Movie>

    @GET("movie/{movie_id}/images")
    suspend fun getImages(@Path("movie_id") movieId: Int)

    @POST("movie/{movie_id}/rating")
    suspend fun postRatingMovie(@Path("movie_id") movieId: Int)

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("keyword") keyword: String,
        @Query("include_adult") includeAdult: Boolean = true
    ): DataModel<Movie>
}
