package com.koma.authlibrary.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.koma.authlibrary.data.source.AuthRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class RegisterViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModel

    private val repository = mock(AuthRepository::class.java)

    @Before
    fun `init`() {
        viewModel = RegisterViewModel(repository)
    }

    @Test
    fun `should return true when in progress`() {
        viewModel.isLoading
    }

    @After
    fun tearDown() {
    }
}