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

package com.koma.movie.data.source

import com.google.common.truth.Truth.assertThat
import com.koma.common.data.entities.Result
import com.koma.movie.data.entities.Movie
import com.koma.movie.data.source.local.LocalDataSource
import com.koma.movie.data.source.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {
    private lateinit var repository: MovieRepository

    private val localDataSource: LocalDataSource = mock()
    private val remoteDataSource: RemoteDataSource = mock()

    @Before
    fun `init`() {
        repository = MovieRepositoryImp(localDataSource, remoteDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return movie data when getPopularMovie successful with forceUpdate is false`() =
        runBlockingTest {
            whenever(localDataSource.getPopularMovie(page = 1)).thenReturn(
                Result.Success(
                    provideMockData()
                )
            )

            val result = repository.getPopularMovie(page = 1, forceUpdate = false)

            verifyZeroInteractions(remoteDataSource)
            assertThat(result is Result.Success).isTrue()
            assertThat((result as Result.Success).data).isNotNull()
            assertThat(result.data).isEqualTo(provideMockData())
        }

    private fun provideMockData() = listOf(
        Movie(100, "", "", "", "", "", "", 1),
        Movie(101, "", "", "", "", "", "", 1),
        Movie(102, "", "", "", "", "", "", 1),
        Movie(103, "", "", "", "", "", "", 1)
    )
}
