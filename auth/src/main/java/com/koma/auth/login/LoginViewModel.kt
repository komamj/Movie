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

package com.koma.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koma.auth.data.source.AuthRepository
import com.koma.common.data.entities.Resource
import com.koma.common.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel constructor(private val repository: AuthRepository) :
    ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _authResult = MutableLiveData<Event<Boolean>>()
    val authResult: LiveData<Event<Boolean>>
        get() = _authResult

    fun login(email: String, password: String) {
        _isLoading.value = true

        viewModelScope.launch {
            val firebaseUser = when (val result = repository.login(email, password)) {
                is Resource.Success -> {
                    _authResult.postValue(Event(true))
                    result.data
                }
                is Resource.Error -> {
                    Timber.e(result.exception)

                    _authResult.postValue(Event(true))

                    null
                }
                else -> {
                    _authResult.postValue(Event(true))

                    null
                }
            }
            _isLoading.postValue(false)
        }
    }
}
