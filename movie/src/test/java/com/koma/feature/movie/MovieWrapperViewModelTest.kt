package com.koma.feature.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.koma.common.data.entities.Result
import com.koma.feature.movie.data.entities.Movie
import com.koma.feature.movie.data.source.MovieRepository
import com.koma.test.LiveDataTestUtil
import com.koma.test.MainCoroutineScopeRule
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
        viewModel = MovieWrapperViewModel(
            repository,
            application
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return true when start invoke`() {
        mainCoroutineRule.pauseDispatcher()

        viewModel.start()

        assertThat(LiveDataTestUtil.getValue(viewModel.isLoading)).isTrue()
    }

    @Test
    fun `should return false when start execute end`() {
        viewModel.start()

        assertThat(LiveDataTestUtil.getValue(viewModel.isLoading)).isFalse()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should return movie list wrapper when start invoke with repository load data successful`() =
        mainCoroutineRule.runBlockingTest {
            `when`(repository.getPopularMovie(anyInt(), anyBoolean())).thenReturn(
                Result.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getNowPlayingMovie(anyInt(), anyBoolean())).thenReturn(
                Result.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getTopRatedMovie(anyInt(), anyBoolean())).thenReturn(
                Result.Success(
                    listOf(
                        mockMovie()
                    )
                )
            )
            `when`(repository.getUpcomingMovie(anyInt(), anyBoolean())).thenReturn(
                Result.Success(
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

    private fun mockMovie() = Movie(1, "", "", "", "", "", "", 1)
}
