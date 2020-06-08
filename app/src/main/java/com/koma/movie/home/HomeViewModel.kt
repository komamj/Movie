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

package com.koma.movie.home

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koma.commonlibrary.data.entities.Result
import com.koma.commonlibrary.util.Event
import com.koma.movie.R
import com.koma.movie.data.entities.Movie
import com.koma.movie.data.source.MovieRepository
import com.koma.movie.home.entities.HomeModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel constructor(
    private val repository: MovieRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>>
        get() = _movie

    private val _homeModelList = MutableLiveData<List<HomeModel>>()
    val homeModelList: LiveData<List<HomeModel>>
        get() = _homeModelList

    fun start() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            Timber.e("error:${throwable.message}")
        }) {
            _isLoading.postValue(true)

            val popularMovie = async {
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
            }

            val homeModelList =
                handleResult(popularMovie, topRatedMovie, nowPlayingMovie, upcomingMovie)

            _homeModelList.postValue(homeModelList)

            _isLoading.postValue(false)
        }
    }

    private suspend fun handleResult(
        popularMovie: Deferred<Result<List<Movie>>>,
        topRatedMovie: Deferred<Result<List<Movie>>>,
        nowPlayingMovie: Deferred<Result<List<Movie>>>,
        upcomingMovie: Deferred<Result<List<Movie>>>
    ): List<HomeModel> {
        val homeModelList = mutableListOf<HomeModel>()

        when (val popularMovieResult = popularMovie.await()) {
            is Result.Success -> {
                val homeModel = HomeModel(
                    getString(R.string.popular_movie),
                    getString(R.string.popular_movie_description),
                    popularMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val topRatedMovieResult = topRatedMovie.await()) {
            is Result.Success -> {
                val homeModel = HomeModel(
                    getString(R.string.top_rated_movie),
                    getString(R.string.top_rated_movie_description),
                    topRatedMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val nowPlayingMovieResult = nowPlayingMovie.await()) {
            is Result.Success -> {
                val homeModel = HomeModel(
                    getString(R.string.now_playing_movie),
                    getString(R.string.now_playing_movie_description),
                    nowPlayingMovieResult.data
                )
                homeModelList.add(homeModel)
            }
        }
        when (val upcomingMovieResult = upcomingMovie.await()) {
            is Result.Success -> {
                val homeModel = HomeModel(
                    getString(R.string.upcoming_movie),
                    getString(R.string.upcoming_movie_description),
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
