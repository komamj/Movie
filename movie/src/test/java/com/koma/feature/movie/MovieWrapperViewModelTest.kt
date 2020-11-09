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
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.koma.common.data.entities.Resource
import com.koma.database.data.entities.Movie
import com.koma.feature.movie.data.source.MovieRepository
import com.koma.test.LiveDataTestUtil
import com.koma.test.MainCoroutineScopeRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class MovieWrapperViewModelTest {
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    private lateinit var viewModel: MovieWrapperViewModel

    private val repository = mock(MovieRepository::class.java)

    private val application = mock(Application::class.java)

    @Before
    fun `init`() {
        `when`(application.getString(anyInt())).thenReturn("")

        viewModel = MovieWrapperViewModel(
            repository,
            coroutineDispatcher = Dispatchers.Main,
            application = application
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return true when start invoke`() = mainCoroutineRule.runBlockingTest {
        mainCoroutineRule.pauseDispatcher()

        viewModel.start()

        assertThat(LiveDataTestUtil.getValue(viewModel.isLoading)).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return false when start execute end`() = mainCoroutineRule.runBlockingTest {
        `when`(repository.getPopularMovie(anyInt(), anyBoolean())).thenReturn(
            Resource.Success(
                listOf(
                    mockMovie()
                )
            )
        )

        viewModel.start()

        assertThat(LiveDataTestUtil.getValue(viewModel.isLoading)).isFalse()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return movie list wrapper when start invoke with repository load data successful`() =
        mainCoroutineRule.runBlockingTest {
            `when`(repository.getPopularMovie(anyInt(), anyBoolean())).thenReturn(
                Resource.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getNowPlayingMovie(anyInt(), anyBoolean())).thenReturn(
                Resource.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getTopRatedMovie(anyInt(), anyBoolean())).thenReturn(
                Resource.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getUpcomingMovie(anyInt(), anyBoolean())).thenReturn(
                Resource.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(application.getString(anyInt())).thenReturn("")

            viewModel.start()

            assertThat(LiveDataTestUtil.getValue(viewModel.homeModelList)).isNotNull()
            assertThat(LiveDataTestUtil.getValue(viewModel.homeModelList)).isNotEmpty()
            assertThat(LiveDataTestUtil.getValue(viewModel.homeModelList).size).isEqualTo(4)
        }

    private fun mockMovie() = Movie("1", "", "", "", "", "", "")
}
