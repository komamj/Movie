package com.koma.splash

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SplashViewModelTest {
    private lateinit var viewModel: SplashViewModel

    @Before
    fun `init`() {
        viewModel = SplashViewModel()
    }
}
