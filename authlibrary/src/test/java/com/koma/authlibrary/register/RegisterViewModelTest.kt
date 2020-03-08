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