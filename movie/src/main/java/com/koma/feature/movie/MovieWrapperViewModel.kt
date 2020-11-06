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

package com.koma.feature.movie

import android.app.Application
import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koma.common.data.entities.Resource
import com.koma.common.util.Event
import com.koma.database.data.entities.Movie
import com.koma.feature.movie.data.entities.MovieWrapper
import com.koma.feature.movie.data.source.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieWrapperViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    application: Application
) : AndroidViewModel(application) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    /* private val _movie = MutableLiveData<List<Movie>>()
     val movie: LiveData<List<Movie>>
         get() = _movie*/

    private val _homeModelList = MutableLiveData<List<MovieWrapper>>()
    val homeModelList: LiveData<List<MovieWrapper>>
        get() = _homeModelList

    fun start() {
        _isLoading.value = true

        viewModelScope.launch {
            val homeModelList = mutableListOf<MovieWrapper>()

            repository.getPopularMovie(PAGE, true)
                .collect {
                    if (it is Resource.Success) {
                        val homeModel = MovieWrapper(
                            getString(R.string.movie_popular_movie),
                            getString(R.string.movie_popular_movie_description),
                            it.data
                        )
                        homeModelList.add(homeModel)
                    }
                }
            /*val popularMovie = async {
                repository.getPopularMovie(PAGE, true)
            }
            val topRatedMovie = async {
                repository.getTopRatedMovie(PAGE, true)
            }
            val nowPlayingMovie = async {
                repository.getNowPlayingMovie(PAGE, true)
            }
            val upcomingMovie = async {
                repository.getUpcomingMovie(PAGE, true)
            }*/

            _homeModelList.postValue(homeModelList)

            _isLoading.value = false
        }
    }

    private suspend fun handleResult(
        popularMovie: Deferred<Resource<List<Movie>>>,
        topRatedMovie: Deferred<Resource<List<Movie>>>,
        nowPlayingMovie: Deferred<Resource<List<Movie>>>,
        upcomingMovie: Deferred<Resource<List<Movie>>>
    ): List<MovieWrapper> {
        val homeModelList = mutableListOf<MovieWrapper>()

        when (val popularMovieResult = popularMovie.await()) {
            is Resource.Success -> {
                val homeModel = MovieWrapper(
                    getString(R.string.movie_popular_movie),
                    getString(R.string.movie_popular_movie_description),
                    popularMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val topRatedMovieResult = topRatedMovie.await()) {
            is Resource.Success -> {
                val homeModel = MovieWrapper(
                    getString(R.string.movie_top_rated_movie),
                    getString(R.string.movie_top_rated_movie_description),
                    topRatedMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val nowPlayingMovieResult = nowPlayingMovie.await()) {
            is Resource.Success -> {
                val homeModel = MovieWrapper(
                    getString(R.string.movie_now_playing_movie),
                    getString(R.string.movie_now_playing_movie_description),
                    nowPlayingMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val upcomingMovieResult = upcomingMovie.await()) {
            is Resource.Success -> {
                val homeModel = MovieWrapper(
                    getString(R.string.movie_upcoming_movie),
                    getString(R.string.movie_upcoming_movie_description),
                    upcomingMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        return homeModelList
    }

    private fun getString(@StringRes resId: Int): String {
        with(getApplication<Application>()) {
            return getString(resId)
        }
    }

    companion object {
        private const val PAGE = 1
    }
}
