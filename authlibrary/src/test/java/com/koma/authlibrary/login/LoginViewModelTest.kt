package com.koma.authlibrary.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.koma.authlibrary.data.source.AuthRepository
import com.koma.testlibrary.LiveDataTestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    private val repository = mock(AuthRepository::class.java)

    @Before
    fun `init`() {
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `should return true when in progress`() =
        runBlockingTest {
            // `when`(repository.login(anyString(), anyString())).thenReturn()
            viewModel.login("junmaokoma@gmail.com", "12345678")

            assertThat(
                LiveDataTestUtil.getValue(viewModel.authResult).getContentIfNotHandled()
            ).isTrue()
        }
}